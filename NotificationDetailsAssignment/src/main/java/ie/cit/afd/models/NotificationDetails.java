package ie.cit.afd.models;

public class NotificationDetails {
	String notificationDetailsID;
	String notificationTypeID;
	String organisationdetailsID;
	String details;
	boolean status;

	public String getNotificationDetailsID() {
		return notificationDetailsID;
	}

	public void setNotificationDetailsID(String notificationDetailsID) {
	   this.notificationDetailsID = notificationDetailsID;
	}

	public String getNotificationTypeID() {
		return notificationTypeID;
	}

	public void setNotificationTypeID(String notificationTypeID) {
		this.notificationTypeID = notificationTypeID;
	}

	public String getOrganisationdetailsID() {
		return organisationdetailsID;
	}

	public void setOrganisationdetailsID(String organisationdetailsID) {
		this.organisationdetailsID = organisationdetailsID;
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NotificationDetails [NotificationDetailsID="
				+ notificationDetailsID + ", NotificationTypeID="
				+ notificationTypeID + ", Details=" + details + ", Status="
				+ status + "]";
	}

}
