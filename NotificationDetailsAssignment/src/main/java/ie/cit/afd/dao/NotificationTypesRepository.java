package ie.cit.afd.dao;

import java.util.List;

import ie.cit.afd.notification.models.*;

public interface NotificationTypesRepository {

	void insert(NotificationTypes notificationtypes);

	void update(NotificationTypes notificationtypes);

	void delete(NotificationTypes notificationtypes);
	NotificationTypes findBycode(String code);
	//NotificationTypes findById(String id);
	List<NotificationTypes> getAll();

}
