package com.brocels.springboot.backend;

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
		
		this.userRepository.save(new User("Fake", "User", "FakeUser", "fake.user@test.com", 666, "Country", "State", "City", "FakePWD666!"));
	}

}
