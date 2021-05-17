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
	
	@Test
	public void should_find_all_users() {
		
		User user1 = new User("TestFirstName1", "TestLastName1", "TestUserName1","TestEmail1", 11, "TestCountry1", "TestState1", "TestCity1", "TestPassword1");
		User user2 = new User("TestFirstName2", "TestLastName2", "TestUserName2","TestEmail2", 22, "TestCountry2", "TestState2", "TestCity2", "TestPassword2");
		User user3 = new User("TestFirstName3", "TestLastName3", "TestUserName3","TestEmail3", 33, "TestCountry3", "TestState3", "TestCity3", "TestPassword3");
		
		Iterable<User> users = repository.findAll();
		
		assertThat(users).contains(user1,user2,user3);
	}
	
	
	
}
