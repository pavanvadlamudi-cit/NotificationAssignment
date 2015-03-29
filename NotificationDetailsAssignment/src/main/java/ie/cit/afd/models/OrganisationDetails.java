package ie.cit.afd.models;

public class OrganisationDetails {
	String OrganisationDetailsID;
	String Name;
	boolean Status;

	@Override
	public String toString() {
		return "OrganisationDetails [OrganisationDetailsID="
				+ OrganisationDetailsID + ", Name=" + Name + ", Status="
				+ Status + "]";
	}

	public String getOrganisationDetailsID() {
		return OrganisationDetailsID;
	}

	public void setOrganisationDetailsID(String organisationDetailsID) {
		OrganisationDetailsID = organisationDetailsID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
}
