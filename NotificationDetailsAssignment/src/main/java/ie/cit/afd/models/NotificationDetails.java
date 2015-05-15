package ie.cit.afd.models;

import java.util.UUID;

public class NotificationDetails {
	String notificationDetailsID;
	String notificationTypeID;
	String notificationTypeCode;
	

	String organisationdetailsID;
	String organisationdetailsName;
	

	String details;
	boolean status;
	public NotificationDetails(){
		this.notificationDetailsID = UUID.randomUUID().toString();	
	}
	
	public String getOrganisationdetailsName() {
		return organisationdetailsName;
	}

	public void setOrganisationdetailsName(String organisationdetailsName) {
		this.organisationdetailsName = organisationdetailsName;
	}
	public String getNotificationTypeCode() {
		return notificationTypeCode;
	}

	public void setNotificationTypeCode(String notificationTypeCode) {
		this.notificationTypeCode = notificationTypeCode;
	}
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
				+ notificationTypeID +", notificationTypeName=" 
				+ notificationTypeCode  
				+ " Details=" + details + ", Status="
				+ status + "]";
	}

}
