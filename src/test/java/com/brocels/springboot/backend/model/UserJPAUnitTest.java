package com.brocels.springboot.backend.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.brocels.springboot.backend.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJPAUnitTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	UserRepository repository;
	
	@Test
	public void should_find_no_users_if_repository_is_empty() {
	
		Iterable<User> users = repository.findAll();
		
		assertThat(users).isEmpty();
	}
	
	@Test
	public void should_store_a_user() {
		
		User user = repository.save(new User("TestFirstName", "TestLastName", "TestUserName","TestEmail", 666, "TestCountry", "TestState", "TestCity", "TestPassword"));
		
		assertThat(user).hasFieldOrProperty("id");
		assertThat(user).hasFieldOrPropertyWithValue("first_name", "TestFirstName");
		assertThat(user).hasFieldOrPropertyWithValue("last_name", "TestLastName");
		assertThat(user).hasFieldOrPropertyWithValue("user_name", "TestUserName");
		assertThat(user).hasFieldOrPropertyWithValue("email", "TestEmail");
		assertThat(user).hasFieldOrPropertyWithValue("age", 666);
		assertThat(user).hasFieldOrPropertyWithValue("country", "TestCountry");
		assertThat(user).hasFieldOrPropertyWithValue("state", "TestState");
		assertThat(user).hasFieldOrPropertyWithValue("city", "TestCity");
		assertThat(user).hasFieldOrPropertyWithValue("password", "TestPassword");
	
	}
	
	
	
}
