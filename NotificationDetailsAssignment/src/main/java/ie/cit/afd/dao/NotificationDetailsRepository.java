package ie.cit.afd.dao;

import java.util.List;
import ie.cit.afd.notification.models.*;

public interface NotificationDetailsRepository {
	void insert(NotificationDetails notificationdetails);

	void update(NotificationDetails notificationdetails);

	void delete(NotificationDetails notificationdetails);
	
	NotificationDetails findById(String notificationDetailsId);

	List<NotificationDetails> getAll();
}
