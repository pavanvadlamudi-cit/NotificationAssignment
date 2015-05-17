package ie.cit.afd.dao;

import java.util.List;

import ie.cit.afd.models.Users;

public interface UsersRepository {
	void insert(Users Users);

	void update(Users Users);

	void delete(Users Users);

	void delete(String id);

	Users findByUsername(String Username);

	List<Users> getAll();
}
