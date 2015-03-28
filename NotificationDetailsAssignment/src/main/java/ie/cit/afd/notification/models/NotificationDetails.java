package ie.cit.afd.notification.models;

public class NotificationDetails {
	String NotificationDetailsID;
	String NotificationTypeID;
	String OrganisationdetailsID;
	String Details;
	boolean Status;

	public String getNotificationDetailsID() {
		return NotificationDetailsID;
	}

	public void setNotificationDetailsID(String notificationDetailsID) {
		NotificationDetailsID = notificationDetailsID;
	}

	public String getNotificationTypeID() {
		return NotificationTypeID;
	}

	public void setNotificationTypeID(String notificationTypeID) {
		NotificationTypeID = notificationTypeID;
	}

	public String getOrganisationdetailsID() {
		return OrganisationdetailsID;
	}

	public void setOrganisationdetailsID(String organisationdetailsID) {
		OrganisationdetailsID = organisationdetailsID;
	}
	
	public String getDetails() {
		return Details;
	}

	public void setDetails(String details) {
		Details = details;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "NotificationDetails [NotificationDetailsID="
				+ NotificationDetailsID + ", NotificationTypeID="
				+ NotificationTypeID + ", Details=" + Details + ", Status="
				+ Status + "]";
	}

}
