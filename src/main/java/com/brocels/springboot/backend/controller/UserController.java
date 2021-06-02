package com.brocels.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
