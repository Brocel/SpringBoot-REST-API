package com.brocels.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brocels.springboot.backend.model.User;
import com.brocels.springboot.backend.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("users")
	public ResponseEntity<List <User>> getUsers(){
		List<User> userList = userRepository.findAll();
		
		if(userList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(userList, HttpStatus.OK);
		}
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> userData = userRepository.findById(id);
		
		if(userData.isPresent()) {
			User _user = userData.get();
			return new ResponseEntity<>(_user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User _user = userRepository.save( new User(
					user.getFirstName(), 
					user.getLastName(),
					user.getUserName(),
					user.getEmail(),
					user.getAge(),
					user.getCountry(),
					user.getState(),
					user.getCity(),
					user.getPassword()
					));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> userData = userRepository.findById(id);
		
		if(userData.isPresent()) {
			User _user = userData.get();
			_user.setFirstName(user.getFirstName());
			_user.setLastName(user.getLastName());
			_user.setUserName(user.getUserName());
			_user.setEmail(user.getEmail());
			_user.setAge(user.getAge());
			_user.setCountry(user.getCountry());
			_user.setState(user.getState());
			_user.setCity(user.getCity());
			_user.setPassword(user.getPassword());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
