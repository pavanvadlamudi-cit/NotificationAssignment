package ie.cit.afd.dao;

import java.util.Collection;
import java.util.List;

import ie.cit.afd.models.Users;

import org.springframework.security.core.GrantedAuthority;

public interface UsersRepository {
	void insert(Users users);

	void update(Users users);

	void delete(Users users);

	void delete(String id);

	Users findByUsername(String Username);

	List<Users> getAll();
	Collection<GrantedAuthority> getAuthorities(Users users);
}
