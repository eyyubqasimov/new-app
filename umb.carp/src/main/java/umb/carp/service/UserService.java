package umb.carp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import umb.carp.dao.UserRepository;
import umb.carp.exception.UserNotFoundException;
import umb.carp.user.User;
import umb.carp.user.UserDTO;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//return all users
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	private User findById(int id) {
		User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non presente per id: " + id));
		return user;
	}
	
	//return a user
	public User getUser(int id) {
		User user = findById(id);
		return user;
	}
	
	public User save(UserDTO userDTO) {//attenzione manca la password
		User user = new User();
		user = setUser(user, userDTO);
		return this.userRepository.save(user);
	}
	
	//delete a user
	public Map<String, Boolean> deleteUser(int id) {
		User user = findById(id);
		this.userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
        response.put("success", Boolean.TRUE);
        return response;
	}
	
	public User updateUser(int id, UserDTO userDTO) {
		User user = findById(id);
		user = setUser(user, userDTO);
		return this.userRepository.save(user);
	}
	
	public User setUser(User user, UserDTO userDTO) {
		user.setName(userDTO.getName());
		user.setLastname(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setProvince(userDTO.getProvince());
		user.setAge(userDTO.getAge());
		user.setFiscalcode(userDTO.getFiscalcode());
		return user;
	}
	
}
