package com.characterBuilder.services.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.repositories.UserMessageRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMessageSrvcTest {

	@Autowired
	UserMessageRepo userMsgRepo;
	
	@Test
	public void test() {
		System.out.println(userMsgRepo.findAll());
	}
}
