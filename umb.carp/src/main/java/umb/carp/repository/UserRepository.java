package umb.carp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umb.carp.model.Item;
import umb.carp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

	User findByEmail(String email);
	
}
