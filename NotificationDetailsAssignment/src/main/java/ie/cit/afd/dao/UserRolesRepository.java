package ie.cit.afd.dao;

import ie.cit.afd.models.UserRoles;

import java.util.List;

public interface UserRolesRepository {
	void insert(UserRoles userroles);

	void update(UserRoles userroles);

	void delete(UserRoles userroles);

	void delete(String id);

	List<UserRoles> findByUsername(String Username);

	List<UserRoles> getAll();
}
