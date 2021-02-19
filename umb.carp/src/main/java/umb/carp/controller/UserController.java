package umb.carp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import umb.carp.dao.UserDao;
import umb.carp.exception.UserNotFoundException;
import umb.carp.user.User;

//@RequestMapping("/users/")
@RestController @CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserDao service;
	
	@GetMapping(value = {"/users/list", "/users/"}, produces = "application/json")
	public List<User> getUsers(){
		return (List<User>) service.findAll();
	}
	
	@GetMapping(value = "/users/{id}", produces = "application/json")
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
	
	@PutMapping("/users/{id}")
	public int saveUser(@PathVariable int id, @RequestBody User userInput) {
		Optional<User> user = this.service.findById(id);
		if(!user.isPresent()) throw new UserNotFoundException("id: "+id);
		user.get().setName(userInput.getName());
		user.get().setLastname(userInput.getLastname());
		user.get().setEmail(userInput.getEmail());
		user.get().setPassword(userInput.getPassword());
		user.get().setPhone(userInput.getPhone());
		user.get().setProvince(userInput.getProvince());
		user.get().setAge(userInput.getAge());
		user.get().setFiscalcode(userInput.getFiscalcode());
		this.service.save(user.get());
		return user.get().getId();
	}
}
