package ie.cit.afd.dao;

import ie.cit.afd.models.NotificationDetails;

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
public class JdbcNotificationDetailsRepository implements
		NotificationDetailsRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcNotificationDetailsRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcNotificationDetailsRepository() {

	}

	public void insert(NotificationDetails notificationDetails) {
		jdbcTemplate
				.update("insert into notificationdetails"
						+ "(notificationdetailsid,notificationtypeid,organisationdetailsid,details,status) "
						+ " values (?,?,?,?,?)", UUID.randomUUID(),
						notificationDetails.getNotificationTypeID(),
						notificationDetails.getOrganisationdetailsID(),
						notificationDetails.getDetails(),
						notificationDetails.isStatus());

	}

	public void update(NotificationDetails notificationDetails) {
		jdbcTemplate.update("update notificationdetails "
				+ "set notificationtypeid=?, " + " organisationdetailsid=?,"
				+ " details=?," + " status=? "
				+ " where notificationdetailsid=?",

		notificationDetails.getNotificationTypeID(),
				notificationDetails.getOrganisationdetailsID(),
				notificationDetails.getDetails(),
				notificationDetails.isStatus(),
				notificationDetails.getNotificationDetailsID());

	}

	public void delete(NotificationDetails notificationDetails) {
		jdbcTemplate.update("delete from notificationdetails "
				+ " where notificationdetailsid=?",
				notificationDetails.getNotificationDetailsID());
	}

	public List<NotificationDetails> getAll() {
		// may be i need a join
		return jdbcTemplate
				.query("select 	"
						+ "ntd.notificationdetailsid,	ntd.notificationtypeid,	nt.code,	"
						+ "ntd.organisationdetailsid,	ntd.details,	ntd.status "
						+ "from notificationdetails ntd "
						+ "inner join notificationtypes nt on "
						+ "ntd.notificationtypeid = nt.notificationtypeid ",
						new NotificationDetailsRowMapper());
	}

	public NotificationDetails findById(String notificationDetailsId) {
		String sql = "select 	"
				+ "ntd.notificationdetailsid,	ntd.notificationtypeid,	nt.code,	"
				+ "ntd.organisationdetailsid,	ntd.details,	ntd.status "
				+ "from notificationdetails ntd "
				+ "inner join notificationtypes nt on "
				+ "ntd.notificationtypeid = nt.notificationtypeid "
				+ " where ntd.notificationdetailsid=?";
		try {
			NotificationDetails notificationDetails;
			notificationDetails = (NotificationDetails) jdbcTemplate
					.queryForObject(sql,
							new Object[] { notificationDetailsId },
							new NotificationDetailsSingleRowMapper());

			return notificationDetails;
		}

		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void delete(String id) {
		jdbcTemplate.update("delete from notificationdetails "
				+ " where notificationdetailsid=?", id);

	}
}

class NotificationDetailsSingleRowMapper implements RowMapper {

	public NotificationDetails mapRow(ResultSet rs, int arg1)
			throws SQLException {

		String notificationDetailsID = rs.getString("notificationdetailsid");
		String notificationTypeID = rs.getString("notificationtypeid");
		String notificationTypeCode = rs.getString("code");
		String organisationdetailsID = rs.getString("organisationdetailsid");
		String details = rs.getString("details");
		Boolean status = rs.getBoolean("status");

		NotificationDetails notificationDetails = new NotificationDetails();
		notificationDetails.setNotificationDetailsID(notificationDetailsID);
		notificationDetails.setNotificationTypeID(notificationTypeID);
		notificationDetails.setOrganisationdetailsID(organisationdetailsID);
		notificationDetails.setDetails(details);
		notificationDetails.setStatus(status);
		notificationDetails.setNotificationTypeCode(notificationTypeCode);
		// notificationTypes.setId(id);
		// notificationTypes.setName(name);
		// notificationTypes.setStatus(status);

		return notificationDetails;
	}
}

class NotificationDetailsRowMapper implements RowMapper<NotificationDetails> {

	public NotificationDetails mapRow(ResultSet rs, int arg1)
			throws SQLException {

		String notificationDetailsID = rs.getString("notificationdetailsid");
		String notificationTypeID = rs.getString("notificationtypeid");
		String organisationdetailsID = rs.getString("organisationdetailsid");
		String details = rs.getString("details");
		Boolean status = rs.getBoolean("status");
		String notificationTypeCode = rs.getString("code");
		NotificationDetails notificationDetails = new NotificationDetails();
		notificationDetails.setNotificationDetailsID(notificationDetailsID);
		notificationDetails.setNotificationTypeID(notificationTypeID);
		notificationDetails.setOrganisationdetailsID(organisationdetailsID);
		notificationDetails.setDetails(details);
		notificationDetails.setStatus(status);
		notificationDetails.setNotificationTypeCode(notificationTypeCode);
		// notificationTypes.setId(id);
		// notificationTypes.setName(name);
		// notificationTypes.setStatus(status);

		return notificationDetails;
	}
}
