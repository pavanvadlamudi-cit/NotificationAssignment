package ie.cit.afd.dao;


import ie.cit.afd.models.OrganisationDetails;

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
public class JdbcOrganisationDetailsRepository implements
		OrganisationDetailsRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcOrganisationDetailsRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(OrganisationDetails organisationDetails) {
		jdbcTemplate.update(
				"insert into  organisationdetails"
						+ "(organisationdetailsid,  name,  status) "
						+ " values (?,?,?)",
						UUID.randomUUID(),
				organisationDetails.getName(), 
				organisationDetails.isStatus());

	}

	public void update(OrganisationDetails organisationDetails) {
		jdbcTemplate.update(
				"update organisationdetails "
						+ "set  name=?,  "
						+ " status=? "
						+ "where organisationdetailsid=?",
				
				organisationDetails.getName(), organisationDetails.isStatus(),
				organisationDetails.getOrganisationDetailsID());


	}

	public void delete(OrganisationDetails organisationDetails) {
		jdbcTemplate.update(
				"delete from organisationdetails "
						+ " where organisationdetailsid=?",
				organisationDetails.getOrganisationDetailsID());

	}
	
	public OrganisationDetails findByname(String name) {
		
		String sql = "select organisationdetailsid,  name,  status "
				+ " from organisationdetails where name=?";
		try{
			OrganisationDetails organisationDetails;
			organisationDetails =(OrganisationDetails) jdbcTemplate.queryForObject(
				sql, new Object[] { name }, new OrganisationDetailsSingleRowMapper());
		
		return organisationDetails;
		}
		
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	public List<OrganisationDetails> getAll() {
		return jdbcTemplate.query(
				"select organisationdetailsid,  name,  status"
						+ " from organisationdetails",
				new OrganisationDetailsRowMapper());
	}

	public void delete(String id) {
		jdbcTemplate.update(
				"delete from organisationdetails "
						+ " where organisationdetailsid=?",
						id);
		
	}

	public OrganisationDetails findById(String id) {
		String sql = "select organisationdetailsid,  name,  status "
				+ " from organisationdetails where organisationdetailsid=?";
		try{
			OrganisationDetails organisationDetails;
			organisationDetails =(OrganisationDetails) jdbcTemplate.queryForObject(
				sql, new Object[] { id }, new OrganisationDetailsSingleRowMapper());
		
		return organisationDetails;
		}
		
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}
}

class OrganisationDetailsSingleRowMapper implements RowMapper {

	public OrganisationDetails mapRow(ResultSet rs, int arg1)
			throws SQLException {

		String organisationDetailsID=rs.getString("organisationdetailsid");
		String name=rs.getString("name");
		Boolean status=rs.getBoolean("status");

		OrganisationDetails organisationDetails = new OrganisationDetails();
		organisationDetails.setOrganisationDetailsID(organisationDetailsID);
		organisationDetails.setName(name);
		organisationDetails.setStatus(status);
		return organisationDetails;
	}
}


class OrganisationDetailsRowMapper implements RowMapper<OrganisationDetails> {

	public OrganisationDetails mapRow(ResultSet rs, int arg1)
			throws SQLException {

		String organisationDetailsID=rs.getString("organisationdetailsid");
		String name=rs.getString("name");
		Boolean status=rs.getBoolean("status");

		OrganisationDetails organisationDetails = new OrganisationDetails();
		organisationDetails.setOrganisationDetailsID(organisationDetailsID);
		organisationDetails.setName(name);
		organisationDetails.setStatus(status);
		return organisationDetails;
	}
}
