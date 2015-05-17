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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
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
						+ " values (?,?,?,?,?)", notificationDetails.getNotificationDetailsID(),
						notificationDetails.getNotificationTypeID(),
						notificationDetails.getOrganisationdetailsID(),
						notificationDetails.getDetails(),
						notificationDetails.isStatus());
		jdbcTemplate.update("insert into owners(notificationdetailsid, username) values(?,?)",
				notificationDetails.getNotificationDetailsID(), 
				SecurityContextHolder.getContext().getAuthentication().getName());
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
	@Transactional(readOnly = true)
	public List<NotificationDetails> getAll() {
		// may be i need a join
		return jdbcTemplate
				.query("select ntd.notificationdetailsid,ntd.notificationtypeid,nt.code,ord.name,"
						+ "ntd.organisationdetailsid,ntd.details,	ntd.status from notificationdetails"
						+ " ntd inner join notificationtypes nt on "
						+ "ntd.notificationtypeid = nt.notificationtypeid 	"
						+ "inner join organisationdetails ord on "
						+ "ntd.organisationdetailsid = ord.organisationdetailsid "
						+ "inner join owners ow on "
								+ "ntd.notificationdetailsid = ow.notificationdetailsid "
								+ "where ow.username = ? ",
								new Object[] { 
										SecurityContextHolder.getContext().getAuthentication().getName() },
						new NotificationDetailsRowMapper());
	}
	@Transactional(readOnly = true)
	public NotificationDetails findById(String notificationDetailsId) {
		String sql = "select ntd.notificationdetailsid,ntd.notificationtypeid,nt.code,ord.name,"
				+ "ntd.organisationdetailsid,ntd.details,	ntd.status from notificationdetails"
				+ " ntd inner join notificationtypes nt on "
				+ "ntd.notificationtypeid = nt.notificationtypeid 	"
				+ "inner join organisationdetails ord on "
				+ "ntd.organisationdetailsid = ord.organisationdetailsid "
				+ "inner join owners ow on "
				+ "ntd.notificationdetailsid = ow.notificationdetailsid "
				+ " where ntd.notificationdetailsid=? and ow.username=?";
		try {
			NotificationDetails notificationDetails;
			notificationDetails = (NotificationDetails) jdbcTemplate
					.queryForObject(sql,
							new Object[] { notificationDetailsId,
							SecurityContextHolder.getContext().getAuthentication().getName() },
							new NotificationDetailsSingleRowMapper());

			return notificationDetails;
		}

		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void delete(String id) {
		jdbcTemplate.update("delete from owners "
				+ " where notificationdetailsid=? and username=?"
				, id,SecurityContextHolder.getContext().getAuthentication().getName());
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
		String organisationdetailsName = rs.getString("name");

		NotificationDetails notificationDetails = new NotificationDetails();
		notificationDetails.setNotificationDetailsID(notificationDetailsID);
		notificationDetails.setNotificationTypeID(notificationTypeID);
		notificationDetails.setOrganisationdetailsID(organisationdetailsID);
		notificationDetails.setDetails(details);
		notificationDetails.setStatus(status);
		notificationDetails.setNotificationTypeCode(notificationTypeCode);
		notificationDetails.setOrganisationdetailsName(organisationdetailsName);
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
		String organisationdetailsName = rs.getString("name");
		
		NotificationDetails notificationDetails = new NotificationDetails();
		notificationDetails.setNotificationDetailsID(notificationDetailsID);
		notificationDetails.setNotificationTypeID(notificationTypeID);
		notificationDetails.setOrganisationdetailsID(organisationdetailsID);
		notificationDetails.setDetails(details);
		notificationDetails.setStatus(status);
		notificationDetails.setNotificationTypeCode(notificationTypeCode);
		notificationDetails.setOrganisationdetailsName(organisationdetailsName);
		// notificationTypes.setId(id);
		// notificationTypes.setName(name);
		// notificationTypes.setStatus(status);

		return notificationDetails;
	}
}
