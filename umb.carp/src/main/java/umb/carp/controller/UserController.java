package umb.carp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import umb.carp.dao.UserDao;
import umb.carp.exception.UserNotFoundException;
import umb.carp.user.User;

//@RequestMapping("/users")
@RestController @CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserDao service;
	
	@GetMapping(value = {"/users/list", "/users/"}, produces = "application/json")
	public List<User> getUsers(){
		return (List<User>) service.findAll();
	}
	
	@GetMapping(value = "/users/{id}", produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable int id) {
//		Optional<User> user = this.service.findById(id);
//		if(!user.isPresent()) throw new UserNotFoundException("id: "+id);
		
		User user = this.service.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non presente per id: " + id));

		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/users/")
	public User createUser(@RequestBody User user) {
		user.setPassword(""+Math.random());
		return this.service.save(user);
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable int id) {
//		Optional<User> user = this.service.findById(id);
		User user = this.service.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non presente per id: " + id));
		this.service.delete(user);
		Map<String, Boolean> response = new HashMap<>();
        response.put("success", Boolean.TRUE);
        return response;
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userInput) {
		User user = this.service.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non presente per id: "+ id));
//		user.get().setName(userInput.getName());
//		user.get().setLastname(userInput.getLastname());
//		user.get().setEmail(userInput.getEmail());
//		user.get().setPassword(userInput.getPassword());
//		user.get().setPhone(userInput.getPhone());
//		user.get().setProvince(userInput.getProvince());
//		user.get().setAge(userInput.getAge());
//		user.get().setFiscalcode(userInput.getFiscalcode());
		final User updatedUser = this.service.save(userInput);
		return ResponseEntity.ok(updatedUser);
	}
	
	
}
