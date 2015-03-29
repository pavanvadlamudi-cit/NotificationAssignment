package ie.cit.afd.models;

public class UserDetails {
	String UserDetailsID;
	String Username;
	String Password;
	String OrganisationDetailsID;
	boolean Status;

	@Override
	public String toString() {
		return "UserDetails [UserDetailsID=" + UserDetailsID + ", Username="
				+ Username + ", Password=" + Password
				+ ", OrganisationDetailsID=" + OrganisationDetailsID
				+ ", Status=" + Status + "]";
	}

	public String getUserDetailsID() {
		return UserDetailsID;
	}

	public void setUserDetailsID(String userDetailsID) {
		UserDetailsID = userDetailsID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getOrganisationDetailsID() {
		return OrganisationDetailsID;
	}

	public void setOrganisationDetailsID(String organisationDetailsID) {
		OrganisationDetailsID = organisationDetailsID;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
}
