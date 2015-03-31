package ie.cit.afd.models;

public class NotificationTypes {
	private String notificationTypeID;
	private String name;
	private String code;
	private boolean status;

	public NotificationTypes() {
		this.notificationTypeID = "";
	}

	public NotificationTypes(String name, String code, boolean status) {
		this.name = name;
		this.code = code;
		this.status = status;
	}

	public String getNotificationTypeID() {
		return notificationTypeID;
	}

	public void setNotificationTypeID(String notificationTypeID) {
		this.notificationTypeID = notificationTypeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NotificationTypes [id=" + notificationTypeID + ", Name=" + name
				+ ", Code=" + code + ", Status=" + status + ", toString()="
				+ super.toString() + "]";
	}
}
