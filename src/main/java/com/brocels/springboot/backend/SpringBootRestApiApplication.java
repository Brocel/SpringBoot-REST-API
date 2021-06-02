package com.brocels.springboot.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brocels.springboot.backend.controller.UserController;
import com.brocels.springboot.backend.model.User;
import com.brocels.springboot.backend.repository.UserRepository;

@SpringBootApplication
public class SpringBootRestApiApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserController userController;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		
		User fakeUserData = userRepository.findByEmail("fake.user@test.com");
		User fakeUser = new User("Fake", "User", "FakeUser", "fake.user@test.com", 666, "FakeCountry", "State", "City", "FakePWD666!");
		
		if ( fakeUserData == null ) {
			this.userRepository.save(fakeUser);
		} else {
			userController.updateUser(fakeUserData.getId(), fakeUser);
		}
		
	}

}
