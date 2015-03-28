package ie.cit.afd.notification.models;

public class NotificationTypes {
	String NotificationTypeID;
	String Name;
	String Code;
	boolean Status;
	public NotificationTypes()
	{
		this.NotificationTypeID="";
	}
	public NotificationTypes(String name, String code, boolean status){
		this.Name=name;
		this.Code=code;
		this.Status= status;
	}
	public String getNotificationTypeID() {
		return NotificationTypeID;
	}

	public void setNotificationTypeID(String notificationTypeID) {
		NotificationTypeID = notificationTypeID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
	@Override
	public String toString() {
		return "NotificationTypes [id=" + NotificationTypeID + ", Name=" + Name + ", Code=" + Code +", Status="
				+ Status + ", toString()=" + super.toString() + "]";
	}
}
