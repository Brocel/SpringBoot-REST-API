package com.brocels.springboot.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brocels.springboot.backend.model.User;
import com.brocels.springboot.backend.repository.UserRepository;

@SpringBootApplication
public class SpringBootRestApiApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		
		User fakeUser = userRepository.findByEmail("fake.user@test.com");
		
		if ( fakeUser == null ) {
			this.userRepository.save(new User("Fake", "User", "FakeUser", "fake.user@test.com", 666, "FakeCountry", "State", "City", "FakePWD666!"));
		} else {
			this.userRepository.delete(fakeUser);
			this.userRepository.save(new User("Fake", "User", "FakeUser", "fake.user@test.com", 666, "FakeCountry", "State", "City", "FakePWD666!"));
		}
		
	}

}
