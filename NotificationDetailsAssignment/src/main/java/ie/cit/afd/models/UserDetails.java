package ie.cit.afd.models;

public class UserDetails {
	String userDetailsID;
	String username;
	String password;
	String organisationDetailsID;
	boolean status;

	@Override
	public String toString() {
		return "UserDetails [userDetailsID=" + userDetailsID + ", Username="
				+ username + ", Password=" + password
				+ ", OrganisationDetailsID=" + organisationDetailsID
				+ ", Status=" + status + "]";
	}

	public String getUserDetailsID() {
		return userDetailsID;
	}

	public void setUserDetailsID(String userDetailsID) {
		this.userDetailsID = userDetailsID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganisationDetailsID() {
		return organisationDetailsID;
	}

	public void setOrganisationDetailsID(String organisationDetailsID) {
		this.organisationDetailsID = organisationDetailsID;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
