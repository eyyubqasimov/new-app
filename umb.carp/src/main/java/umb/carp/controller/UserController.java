package umb.carp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import umb.carp.dao.UserDao;
import umb.carp.exception.UserNotFoundException;
import umb.carp.user.User;

@RestController
//@RequestMapping("/users/")
public class UserController {

	@Autowired
	private UserDao service;
	
	@GetMapping("/users/list")
	public List<User> getUsers(){
		
		return (List<User>) service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable int id) {
		Optional<User> user = this.service.findById(id);
		if(!user.isPresent()) throw new UserNotFoundException("id: "+id);
		return user;
	}
	
	@PostMapping("/users/")
	public int saveUser(@RequestBody User user) {
		this.service.save(user);
		return user.getId();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Optional<User> user = this.service.findById(id);
		if(!user.isPresent()) throw new UserNotFoundException("id: "+id);
		this.service.delete(user.get());
	}
	
}
