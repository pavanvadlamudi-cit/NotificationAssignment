package ie.cit.afd.dao;

import ie.cit.afd.models.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDetailsRepository implements UserDetailsRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcUserDetailsRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(UserDetails userDetails) {
		
		jdbcTemplate.update("insert into "
						+ "userdetails"
						+ "(userdetailsid,  username,  password,  organisationdetailsid,  status) "
						+ " values (?,?,?,?,?)", 
						UUID.randomUUID(),
						userDetails.getUsername(), userDetails.getPassword(),
						userDetails.getOrganisationDetailsID(),
						userDetails.isStatus());
	}

	public void update(UserDetails userDetails) {
		jdbcTemplate.update("update "
				+ "userdetails "
				+ " set username=?,"
				+ " password=?,"
				+ " organisationdetailsid=?,"
				+ " status=?"
				+ " where userdetailsid=?"
				, 
				
				userDetails.getUsername(), userDetails.getPassword(),
				userDetails.getOrganisationDetailsID(),
				userDetails.isStatus(),
				userDetails.getUserDetailsID());

	}

	public void delete(UserDetails userDetails) {
		jdbcTemplate.update("delete "
				+ "from userdetails "
				+ "where userdetailsid=?"
				, 
				userDetails.getUserDetailsID());

	}

	public List<UserDetails> getAll() {
		return jdbcTemplate.query("select "
						+ "userdetailsid,  username,  password,  organisationdetailsid,  status"
						+ " from userdetails", new UserDetailsRowMapper());

	}

	public UserDetails findByusername(String username) {
		String sql = "select userdetailsid,  username,  password,  organisationdetailsid,  status "
				+ " from userdetails where username=?";
		try{
			UserDetails userDetails;
			userDetails =(UserDetails) jdbcTemplate.queryForObject(
				sql, new Object[] { username }, new UserDetailsSingleRowMapper());
		
		return userDetails;
		}
		
		catch(EmptyResultDataAccessException e){
			return null;
		}
		
	}

	public void delete(String id) {
		jdbcTemplate.update("delete "
				+ "from userdetails "
				+ "where userdetailsid=?"
				, 
				id);
		
	}

	public UserDetails findById(String id) {
		String sql = "select userdetailsid,  username,  password,  organisationdetailsid,  status "
				+ " from userdetails where userdetailsid=?";
		try{
			UserDetails userDetails;
			userDetails =(UserDetails) jdbcTemplate.queryForObject(
				sql, new Object[] { id }, new UserDetailsSingleRowMapper());
		
		return userDetails;
		}
		
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}
}

class UserDetailsSingleRowMapper implements RowMapper{

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
}

class UserDetailsRowMapper implements RowMapper<UserDetails> {

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
}
