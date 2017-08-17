package com.characterBuilder.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.services.interfaces.DescriptionSrvc;
import com.characterBuilder.services.interfaces.UserSrvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptionSrvcTest {

	@Autowired
	DescriptionSrvc descSrvc;
	
	@Autowired
	UserSrvc userSrvc;
	
	@Test
	@Transactional
	public void testGetEventDesc (){
		assert(descSrvc.getEventDescription(2).contains("Bodega!"));
		assert(descSrvc.getAllEventDesc().size() == 2);
		assert(descSrvc.getEventDescription(1).contains("Booya!"));
		assert(descSrvc.getEventDescription(0) == null);
		
		assert(descSrvc.getEventDescription(3) == null);
	}
	
	@Test
	@Transactional
	public void testGetSkillDesc () {
		assert(descSrvc.getAllSkillDesc().size() == 2);
		assert(descSrvc.getSkillDescription(2).equals(
				"In all seriousness I think this is a powerful tool that is worth "
				+ "the trouble. Now users can provide as much or as little information "
				+ "as they want. Without harsh memory costs to the system... Enjoy!"));
		assert(descSrvc.getSkillDescription(1).equals(
				"This is the skill that never ends. It goes on and on my friends. some "
				+ "people started concatinating not knowing what it was and they just kept "
				+ "concatinating forever just because"));
		assert(descSrvc.getSkillDescription(0) == null);
		
		assert(descSrvc.getSkillDescription(3) == null);
	}
}
