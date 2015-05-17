package ie.cit.afd.dao;

import ie.cit.afd.models.UserRoles;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JdbcUserRolesRepository  implements UserRolesRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcUserRolesRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(UserRoles userroles) {
		jdbcTemplate.update("INSERT INTO user_roles(username, role) VALUES (?, ?)"
				,userroles.getUsername(),userroles.getRole());
	}

	public void update(UserRoles userroles) {
		jdbcTemplate.update("UPDATE user_roles SET username=?, role=?, WHERE user_role_id=?",
				userroles.getUsername(),userroles.getRole(),userroles.getUser_role_id());
	}

	public void delete(UserRoles userroles) {
		jdbcTemplate.update("DELETE FROM user_roles WHERE  user_role_id=?",userroles.getUser_role_id());
		
	}

	public void delete(String username) {
		jdbcTemplate.update("DELETE FROM user_roles WHERE  username=?",username);
		
	}

	public List<UserRoles> findByUsername(String Username) {
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

	public List<UserRoles> getAll() {
		return jdbcTemplate.query("SELECT username, role, user_role_id FROM user_roles "
				, new UserRolesSingleRowMapper());
		
	}

	public UserRoles mapRow(ResultSet rs, int arg1) throws SQLException {

		String username = rs.getString("username");
		String role = rs.getString("role");
		int user_role_id =Integer.parseInt( rs.getString("user_role_id"));

		UserRoles userRoles = new UserRoles();
		
		userRoles.setUsername(username);
		userRoles.setRole(role);
		userRoles.setUser_role_id(user_role_id);		

		return userRoles;
	}
}
class UserRolesSingleRowMapper implements RowMapper<UserRoles> {

	public UserRoles mapRow(ResultSet rs, int arg1) throws SQLException {

		String username = rs.getString("username");
		String role = rs.getString("role");
		int user_role_id =Integer.parseInt( rs.getString("user_role_id"));

		UserRoles userRoles = new UserRoles();
		
		userRoles.setUsername(username);
		userRoles.setRole(role);
		userRoles.setUser_role_id(user_role_id);		

		return userRoles;
	}
}

	

