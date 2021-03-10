package umb.carp.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import umb.carp.dao.UserRepository;
import umb.carp.exception.UserNotFoundException;
import umb.carp.exception.UsernameAlreadyPresentException;
import umb.carp.user.User;
import umb.carp.user.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public UserDetails loadUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("User not found with email: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	//load username from email
	public String loadUsernameByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("User not found with email: " + email);
		}
		return user.getUsername();
	}
	
	public User save(UserDTO user) {
		User newUser = userRepository.findByEmail(user.getEmail());
		if (newUser != null) {
			throw new UsernameAlreadyPresentException("User already found with email: " + user.getEmail());
		}
		
		newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setName(user.getName());
		newUser.setLastname(user.getLastname());
		newUser.setEmail(user.getEmail());
		newUser.setPhone(user.getPhone());
		newUser.setProvince(user.getProvince());
		newUser.setAge(user.getAge());
		newUser.setFiscalcode(user.getFiscalcode());
		return userRepository.save(newUser);
	}
}