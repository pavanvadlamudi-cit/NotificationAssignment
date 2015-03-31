package ie.cit.afd.models;

public class OrganisationDetails {
	String organisationDetailsID;
	String name;
	boolean status;

	@Override
	public String toString() {
		return "OrganisationDetails [OrganisationDetailsID="
				+ organisationDetailsID + ", Name=" + name + ", Status="
				+ status + "]";
	}

	public String getOrganisationDetailsID() {
		return organisationDetailsID;
	}

	public void setOrganisationDetailsID(String organisationDetailsID) {
		this.organisationDetailsID = organisationDetailsID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
