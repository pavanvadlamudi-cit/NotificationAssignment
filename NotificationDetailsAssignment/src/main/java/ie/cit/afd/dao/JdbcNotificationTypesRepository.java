package ie.cit.afd.dao;

import ie.cit.afd.models.NotificationTypes;

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
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JdbcNotificationTypesRepository implements
		NotificationTypesRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcNotificationTypesRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(NotificationTypes notificationTypes) {
		jdbcTemplate.update("insert into  notificationtypes"
				+ "(notificationtypeid,name,code,status) "
				+ " values (?,?,?,?)", UUID.randomUUID(),
				notificationTypes.getName(), notificationTypes.getCode(),
				notificationTypes.isStatus());

	}

	public void update(NotificationTypes notificationTypes) {
		jdbcTemplate.update("update notificationtypes " + "set name=?, "
				+ " code=?, " + " status=? " + " where notificationtypeid=?",
				notificationTypes.getName(), notificationTypes.getCode(),
				notificationTypes.isStatus(),
				notificationTypes.getNotificationTypeID());

	}

	public void delete(NotificationTypes notificationTypes) {
		jdbcTemplate.update(
				"delete from notificationtypes where notificationtypeid=?",
				notificationTypes.getNotificationTypeID());

	}

	@Transactional(readOnly = true)
	public List<NotificationTypes> getAll() {
		return jdbcTemplate.query("select notificationtypeid,name,code,status"
				+ " from notificationtypes", new NotificationTypesRowMapper());

	}

	@Transactional(readOnly = true)
	public NotificationTypes findBycode(String code) {

		String sql = "select notificationtypeid,name,code,status"
				+ " from notificationtypes where code=?";
		try {
			NotificationTypes notificationTypes;
			notificationTypes = (NotificationTypes) jdbcTemplate
					.queryForObject(sql, new Object[] { code },
							new NotificationTypesSingleRowMapper());

			return notificationTypes;
		}

		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void delete(String id) {
		jdbcTemplate.update(
				"delete from notificationtypes where notificationtypeid=?", id);

	}

	@Transactional(readOnly = true)
	public NotificationTypes findById(String notificationtypeid) {
		String sql = "select notificationtypeid,name,code,status"
				+ " from notificationtypes where notificationtypeid=?";
		try {
			NotificationTypes notificationTypes;
			notificationTypes = (NotificationTypes) jdbcTemplate
					.queryForObject(sql, new Object[] { notificationtypeid },
							new NotificationTypesSingleRowMapper());

			return notificationTypes;
		}

		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

class NotificationTypesSingleRowMapper implements RowMapper {
	public NotificationTypes mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		String notificationTypeID = rs.getString("notificationtypeid");
		String name = rs.getString("name");
		String code = rs.getString("code");
		Boolean status = rs.getBoolean("status");

		NotificationTypes notificationTypes = new NotificationTypes();
		notificationTypes.setNotificationTypeID(notificationTypeID);
		notificationTypes.setName(name);
		notificationTypes.setStatus(status);
		notificationTypes.setCode(code);

		return notificationTypes;
	}

}

class NotificationTypesRowMapper implements RowMapper<NotificationTypes> {

	public NotificationTypes mapRow(ResultSet rs, int arg1) throws SQLException {

		String notificationTypeID = rs.getString("notificationtypeid");
		String name = rs.getString("name");
		String code = rs.getString("code");
		Boolean status = rs.getBoolean("status");

		NotificationTypes notificationTypes = new NotificationTypes();
		notificationTypes.setNotificationTypeID(notificationTypeID);
		notificationTypes.setName(name);
		notificationTypes.setStatus(status);
		notificationTypes.setCode(code);

		return notificationTypes;
	}
}
