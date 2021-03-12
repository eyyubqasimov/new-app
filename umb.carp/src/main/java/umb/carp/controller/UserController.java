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

import umb.carp.dao.UserRepository;
import umb.carp.exception.UserNotFoundException;
import umb.carp.service.UserService;
import umb.carp.user.User;
import umb.carp.user.UserDTO;

//@RequestMapping("/users")
@RestController @CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	//private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = {"/users/list", "/users/"}, produces = "application/json")
	public List<User> getUsers(){
		return this.userService.getUsers();
	}
	
	@GetMapping(value = "/users/{id}", produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable int id) {
//		Optional<User> user = this.service.findById(id);
//		if(!user.isPresent()) throw new UserNotFoundException("id: "+id);
		
		User user = this.userService.getUser(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/users/")
	public User createUser(@RequestBody UserDTO user) {
		return this.userService.save(user);//VERIFICARE SE L'UTENTE è GIA PRESENTE!!!
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable int id) {
		Map<String, Boolean> response = this.userService.deleteUser(id);
        return response;
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserDTO userInput) {
		User user = userService.updateUser(id, userInput);
		return ResponseEntity.ok(user);
	}
	
	
}
