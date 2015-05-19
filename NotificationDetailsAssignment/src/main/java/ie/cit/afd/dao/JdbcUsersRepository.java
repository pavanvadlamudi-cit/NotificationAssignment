package ie.cit.afd.dao;


import ie.cit.afd.models.UserRoles;
import ie.cit.afd.models.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JdbcUsersRepository implements UsersRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcUsersRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(Users users) {
		String password;
		password = new BCryptPasswordEncoder().encode(users.getPassword());
		jdbcTemplate.update("INSERT INTO users("
				+ "username, password, enabled)  VALUES (?, ?, ?)",
				users.getUsername(), password, users.isEnabled());
		jdbcTemplate.update("insert into authorities values(?,'ROLE_USER')",users.getUsername());	
	}

	public void update(Users users) {
		String password;
		password = new BCryptPasswordEncoder().encode(users.getPassword());
		jdbcTemplate.update("update users set password=? where username=?",
				password, users.getUsername());

	}

	public void delete(String username) {
		jdbcTemplate.update("delete from owners where username=?", username);
		jdbcTemplate.update("delete from authorities where username=?", username);
		jdbcTemplate.update("delete from users where username=?", username);
	}

	@Transactional(readOnly = true)
	public Users findByUsername(String Username) {
		String sql = "select username,  password,  enabled "
				+ " from users where username=?";
		try {
			Users users;
			users = (Users) jdbcTemplate.queryForObject(sql,
					new Object[] { Username }, new UsersSingleRowMapper());

			return users;
		}

		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<Users> getAll() {
		return jdbcTemplate.query("select " + "username,  password,  enabled"
				+ " from users", new UsersSingleRowMapper());

	}

	public Users mapRow(ResultSet rs, int arg1) throws SQLException {

		String username = rs.getString("username");
		String password = rs.getString("password");
		Boolean status = rs.getBoolean("enabled");

		Users userDetails = new Users();
		
		userDetails.setUsername(username);
		userDetails.setEnabled(status);
		
		userDetails.setPassword(password);

		return userDetails;
	}

	public Collection<GrantedAuthority> getAuthorities(Users users) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<UserRoles> roles = getRolesByUsername(users.getUsername());
		for (UserRoles role : roles) {
			GrantedAuthority ga = new SimpleGrantedAuthority(role.getRole());
			authorities.add(ga);
		}
		return authorities;
	}

	public List<UserRoles> getRolesByUsername(String Username) {
		List<UserRoles> userRolesList = new ArrayList<UserRoles>();
		String sql = "SELECT username, authority, 1 as user_role_id FROM authorities WHERE  username=?";
		try {
			return jdbcTemplate.query(sql, new Object[] { Username },
					new UserRolesSingleRowMapper());
			// return userRoles;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void delete(Users users) {
		jdbcTemplate.update("delete from owners where username=?", users.getUsername());
		jdbcTemplate.update("delete from authorities where username=?", users.getUsername());
		jdbcTemplate.update("delete from users where username=?", users.getUsername());

		
	}

}

class UsersSingleRowMapper implements RowMapper<Users> {
	public Users mapRow(ResultSet rs, int arg1) throws SQLException {
		String username = rs.getString("username");
		String password = rs.getString("password");
		Boolean enabled = rs.getBoolean("enabled");
		Users userDetails = new Users();

		userDetails.setUsername(username);
		userDetails.setPassword(password);
		userDetails.setEnabled(enabled);

		return userDetails;
	}
}
