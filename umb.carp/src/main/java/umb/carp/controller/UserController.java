package umb.carp.controller;

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

import umb.carp.dto.ItemDTO;
import umb.carp.model.Item;
import umb.carp.service.UserService;

//@RequestMapping("/users")
@RestController @CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	//private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = {"/users/list", "/users/"}, produces = "application/json")
	public List<Item> getItems(){
		return this.userService.getItems();
	}
	
	@GetMapping(value = "/users/{id}", produces = "application/json")
	public ResponseEntity<Item> getUser(@PathVariable int id) {
//		Optional<User> user = this.service.findById(id);
//		if(!user.isPresent()) throw new UserNotFoundException("id: "+id);
		Item item = this.userService.getItem(id);
		return ResponseEntity.ok().body(item);
	}
	
	@PostMapping("/users/")
	public Item createItem(@RequestBody ItemDTO item) {
		return this.userService.save(item);//VERIFICARE SE L'UTENTE è GIA PRESENTE!!!
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable int id) {
		Map<String, Boolean> response = this.userService.deleteItem(id);
        return response;
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody ItemDTO itemInput) {
		Item item = userService.updateItem(id, itemInput);
		return ResponseEntity.ok(item);
	}
	
	
}
