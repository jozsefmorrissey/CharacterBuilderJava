package com.characterBuilder.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.User;
import com.characterBuilder.repositories.SkillMapRepo;
import com.characterBuilder.services.interfaces.SkillMapSrvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillMapSrvcTest {
	
	@Autowired
	SkillMapSrvc skillMapSrvc;
	
	@Autowired
	SkillMapRepo skillMapRepo;
	
	private User user;
	
	@Before
	public void initialize() {
		user = new User();
		user.setId(1L);
	}
	
	@Test
	@Transactional
	public void testGetAll() {
//		System.out.println("\n\n" + skillMapRepo.getOne(3L) + "\n\n");
	}
}