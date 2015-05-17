package ie.cit.afd.models;

public class Users {
	String username;
	String password;
	boolean enabled;

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Users [User name=" + username + ", Password=" + password
				+ ", enabled=" + enabled + "]";
	}

}
