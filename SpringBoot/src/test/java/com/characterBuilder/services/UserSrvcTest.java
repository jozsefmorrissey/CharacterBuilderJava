package com.characterBuilder.services;

import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Permission;
import com.characterBuilder.services.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegisteredException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSrvcTest {
	
	@Autowired
	private UserSrvc userSrvc;
	
	private User user;
	
	@Before
	public void intialize() {
		String name = "Bill Dance";
		String email = "bill@dance.com";
		long phoneNumber = 18008675309L;
		Permission permission = new Permission();
		permission.setId(1);
		String password = "disco4life";
		int lastLocation = 0;
		
		user = new User(0, name, email, phoneNumber, permission, password, lastLocation);
	}


	@Test
	@Transactional
	public void testUpdate() {
		addUserSuccessfully();
		
		user = userSrvc.getByEmail(user.getEmail());
		String newName = "David Copperfield";
		user.setName(newName);
		userSrvc.update(user);
		user = userSrvc.getByEmail(user.getEmail());
		assert(user.getName().equals(newName));
		userSrvc.delete(user);
	}


	@Test
	@Transactional
	public void testDelete() {
		addUserSuccessfully();
		
		userSrvc.delete(user);
		try {
			userSrvc.add(user);
			assert(true);
		} catch (EmailAlreadyRegisteredException e) {
			assert(false);
		}
		userSrvc.delete(user);
	}


	@Test
	@Transactional
	public void testAdd() {
		addUserSuccessfully();
		try {
			userSrvc.add(user);
			fail("Add should throw EmailAlreadyRegisteredException");
		} catch (EmailAlreadyRegisteredException e) {
			assert(true);
		}
		userSrvc.delete(user);
	}



	@Test
	@Transactional
	public void testGetUser() {
		addUserSuccessfully();
		User ret = userSrvc.getById(user.getId());
		assert(ret.getId() == user.getId());
		userSrvc.delete(user);
	}
	
	@Test
	@Transactional
	public void testGetByEmail() {
		addUserSuccessfully();
		user = userSrvc.getByEmail(user.getEmail());
		assert(user.getEmail().equals(user.getEmail()));
		userSrvc.delete(user);
	}

	@Test
	public void testGetAllUsers() {
		Set<User> users = userSrvc.getAll();
		int intialSize = users.size();
		
		addUserSuccessfully();

		users = userSrvc.getAll();
		assert(users.size() == intialSize + 1);
		
		userSrvc.delete(user);

		users = userSrvc.getAll();
		assert(users.size() == intialSize);
	}
	
	private void addUserSuccessfully() {
		try {
			userSrvc.add(user);
		} catch (EmailAlreadyRegisteredException e) {
			assert(false);
		}
	}
}
