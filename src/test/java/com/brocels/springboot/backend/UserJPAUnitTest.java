package com.brocels.springboot.backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.brocels.springboot.backend.model.User;
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
		
		User user = repository.save(new User("TestFirstName", "TestLastName", "TestUserName","TestEmail@test.com", 666, "TestCountry", "TestState", "TestCity", "TestPassword"));
		
		assertThat(user).hasFieldOrProperty("id");
		assertThat(user).hasFieldOrPropertyWithValue("firstName", "TestFirstName");
		assertThat(user).hasFieldOrPropertyWithValue("lastName", "TestLastName");
		assertThat(user).hasFieldOrPropertyWithValue("userName", "TestUserName");
		assertThat(user).hasFieldOrPropertyWithValue("email", "TestEmail@test.com");
		assertThat(user).hasFieldOrPropertyWithValue("age", 666);
		assertThat(user).hasFieldOrPropertyWithValue("country", "TestCountry");
		assertThat(user).hasFieldOrPropertyWithValue("state", "TestState");
		assertThat(user).hasFieldOrPropertyWithValue("city", "TestCity");
		assertThat(user).hasFieldOrPropertyWithValue("password", "TestPassword");
	
	}
	
	@Test
	public void should_find_all_users() {
		
		User user1 = new User("TestFirstName1", "TestLastName1", "TestUserName1","TestEmail1@test.com", 11, "TestCountry1", "TestState1", "TestCity1", "TestPassword1");
		User user2 = new User("TestFirstName2", "TestLastName2", "TestUserName2","TestEmail2@test.com", 22, "TestCountry2", "TestState2", "TestCity2", "TestPassword2");
		User user3 = new User("TestFirstName3", "TestLastName3", "TestUserName3","TestEmail3@test.com", 33, "TestCountry3", "TestState3", "TestCity3", "TestPassword3");
		
		Iterable<User> users = repository.findAll();
		
		assertThat(users).hasSize(3).contains(user1,user2,user3);
	}
	
	@Test
	public void should_find_user_by_id() {
		
		User user1 = new User("TestFirstName1", "TestLastName1", "TestUserName1","TestEmail1@test.com", 11, "TestCountry1", "TestState1", "TestCity1", "TestPassword1");
		entityManager.persist(user1);
		
		User user2 = new User("TestFirstName2", "TestLastName2", "TestUserName2","TestEmail2@test.com", 22, "TestCountry2", "TestState2", "TestCity2", "TestPassword2");
		entityManager.persist(user2);
		
		User foundUser = repository.findById(user2.getId()).get();
		
		assertThat(foundUser).isEqualTo(user2);
		
	}
	
	@Test
	public void should_find_user_by_username() {
		
		User user1 = new User("TestFirstName1", "TestLastName1", "TestUserName1","TestEmail1@test.com", 11, "TestCountry1", "TestState1", "TestCity1", "TestPassword1");
		entityManager.persist(user1);
		
		User user2 = new User("TestFirstName2", "TestLastName2", "TestUserName2","TestEmail2@test.com", 22, "TestCountry2", "TestState2", "TestCity2", "TestPassword2");
		entityManager.persist(user2);
		
		User user3 = new User("TestFirstName3", "TestLastName3", "TestUserName3","TestEmail3@test.com", 33, "TestCountry3", "TestState3", "TestCity3", "TestPassword3");
		entityManager.persist(user3);
		
		User foundUser = repository.findByUserName("TestUserName2");
		
		assertThat(foundUser).isEqualTo(user2);
	
	}
	
	@Test
	public void should_find_user_by_email() {
		
		User user1 = new User("TestFirstName1", "TestLastName1", "TestUserName1","TestEmail1@test.com", 11, "TestCountry1", "TestState1", "TestCity1", "TestPassword1");
		entityManager.persist(user1);
		
		User user2 = new User("TestFirstName2", "TestLastName2", "TestUserName2","TestEmail2@test.com", 22, "TestCountry2", "TestState2", "TestCity2", "TestPassword2");
		entityManager.persist(user2);
		
		User user3 = new User("TestFirstName3", "TestLastName3", "TestUserName3","TestEmail1@test.com", 33, "TestCountry3", "TestState3", "TestCity3", "TestPassword3");
		entityManager.persist(user3);
		
		User user = repository.findByEmail("TestEmail2@test.com");
		
		assertThat(user).isEqualTo(user2);
	}
	
	@Test
	public void should_find_users_by_country() {
		
		User user1 = new User("TestFirstName1", "TestLastName1", "TestUserName1","TestEmail1", 11, "TestCountry1", "TestState1", "TestCity1", "TestPassword1");
		entityManager.persist(user1);
		
		User user2 = new User("TestFirstName2", "TestLastName2", "TestUserName2","TestEmail2", 22, "TestCountry2", "TestState2", "TestCity2", "TestPassword2");
		entityManager.persist(user2);
		
		User user3 = new User("TestFirstName3", "TestLastName3", "TestUserName3","TestEmail3", 33, "TestCountry1", "TestState3", "TestCity3", "TestPassword3");
		entityManager.persist(user3);
		
		Iterable<User> usersByCountry = repository.findByCountry("TestCountry1");
		
		assertThat(usersByCountry).hasSize(2).contains(user1, user3);
	}
	
	@Test
	public void should_find_users_by_state() {
		
		User user1 = new User("TestFirstName1", "TestLastName1", "TestUserName1","TestEmail1", 11, "TestCountry1", "TestState1", "TestCity1", "TestPassword1");
		entityManager.persist(user1);
		
		User user2 = new User("TestFirstName2", "TestLastName2", "TestUserName2","TestEmail2", 22, "TestCountry2", "TestState2", "TestCity2", "TestPassword2");
		entityManager.persist(user2);
		
		User user3 = new User("TestFirstName3", "TestLastName3", "TestUserName3","TestEmail3", 33, "TestCountry2", "TestState2", "TestCity3", "TestPassword3");
		entityManager.persist(user3);
		
		Iterable<User> usersByState = repository.findByState("TestState2");
		
		assertThat(usersByState).hasSize(2).contains(user2, user3);
	}
	
}
