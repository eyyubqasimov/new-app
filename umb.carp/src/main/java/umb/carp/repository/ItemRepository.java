package umb.carp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import umb.carp.model.Item;
import umb.carp.model.User;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	User findByEmail(String email);
	
	User findByUsername(String username);
	
}