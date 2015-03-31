package ie.cit.afd.dao;

import java.util.List;

import ie.cit.afd.models.*;

public interface UserDetailsRepository {
	void insert(UserDetails userdetails);

	void update(UserDetails userdetails);

	void delete(UserDetails userdetails);

	void delete(String id);

	UserDetails findByusername(String username);

	UserDetails findById(String id);

	List<UserDetails> getAll();
}
