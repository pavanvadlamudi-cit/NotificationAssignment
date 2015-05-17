package ie.cit.afd.dao;

import ie.cit.afd.models.UserDetails;
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
public class JdbcUsersRepository implements UsersRepository{
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcUsersRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void insert(Users users) {
		String password;
		password =new BCryptPasswordEncoder().encode(users.getPassword());
		jdbcTemplate.update("INSERT INTO users("
            +"username, password, enabled)  VALUES (?, ?, ?)",
            users.getUsername(), password,users.isEnabled());

	}

	public void update(Users users) {
		String password;
		password =new BCryptPasswordEncoder().encode(users.getPassword());
		jdbcTemplate.update("update users set password=? where username=?"
            ,password,
            users.getUsername());
		
	}

	public void delete(Users users) {
		jdbcTemplate.update("delete users where username=?",
	               users.getUsername());
		
	}

	public void delete(String username) {
		jdbcTemplate.update("delete users where username=?",
				username);
		
	}
	@Transactional(readOnly = true)
	public Users findByUsername(String Username) {
		String sql = "select username,  password,  enabled "
				+ " from users where username=?";
		try {
			Users users;
			users = (Users) jdbcTemplate
					.queryForObject(sql, new Object[] { Username },
							new UsersSingleRowMapper());

			return users;
		}

		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	@Transactional(readOnly = true)
	public List<Users> getAll() {
		return jdbcTemplate.query("select "
						+ "username,  password,  enabled"
						+ " from users", new UsersSingleRowMapper());
		
	}
	
	public UserDetails mapRow(ResultSet rs, int arg1) throws SQLException {

		String userDetailsID = rs.getString("userdetailsid");
		String username = rs.getString("username");
		String organisationDetailsID = rs.getString("organisationdetailsid");
		String password = rs.getString("password");
		Boolean status = rs.getBoolean("status");

		UserDetails userDetails = new UserDetails();
		userDetails.setUserDetailsID(userDetailsID);
		userDetails.setUsername(username);
		userDetails.setStatus(status);
		userDetails.setOrganisationDetailsID(organisationDetailsID);
		userDetails.setPassword(password);

		return userDetails;
	}

	public Collection<GrantedAuthority> getAuthorities(Users users) {
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		List<UserRoles> roles= getRolesByUsername(users.getUsername());
		for (  UserRoles role : roles) {
		    GrantedAuthority ga=new SimpleGrantedAuthority(role.getRole());
		    authorities.add(ga);
		  }
		return authorities;
	}
	public List<UserRoles> getRolesByUsername(String Username){
		List<UserRoles> userRolesList = new ArrayList<UserRoles>();
		String sql="SELECT username, role, user_role_id FROM user_roles WHERE  username=?";
		try {
			return jdbcTemplate.query(sql, new Object[] { Username },
							new UserRolesSingleRowMapper());
			//return userRoles;
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
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
