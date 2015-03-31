package ie.cit.afd.dao;

import java.util.List;

import ie.cit.afd.models.*;

public interface OrganisationDetailsRepository {
	void insert(OrganisationDetails organisationdetails);

	void update(OrganisationDetails organisationdetails);

	void delete(OrganisationDetails organisationdetails);

	void delete(String id);

	OrganisationDetails findByname(String name);

	OrganisationDetails findById(String id);

	List<OrganisationDetails> getAll();
}
