package com.characterBuilder.compare;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.UserMessage;
import com.characterBuilder.entities.pureDBEntities.Permission;
import com.characterBuilder.util.TestUtilities;

public class UserMessageCompareTest
{
	ArrayList<User> users = new ArrayList<User>();
	
	ArrayList<UserMessage> allMessages;
	ArrayList<UserMessage> oneConversation;
	
    Random rnd = new Random();

    int userCount = 10;
    
	@Before
	public void initialize() {
		allMessages = new ArrayList<UserMessage>();
		oneConversation = new ArrayList<UserMessage>();
		createUsers();
		createMessages();
	}
	
	@Test
	public void testIsSameConversation() {
		for(int i = 0; i < oneConversation.size(); i++) {
			if(!oneConversation.get(i).isSameConversation(oneConversation.get(0)))
				fail("Messages were not considered to be the same conversation:\n\t1: " + 
						oneConversation.get(i) + "\n\t2: " + oneConversation.get(0));
		}
	}
	
	@Test
	public void testEmailCompare() {
		int eqCount = 0;
		for(int i = 0; i < allMessages.size(); i++) {
			for(int j = 0; j < allMessages.size(); j++) {
				UserMessage iMsg = allMessages.get(i);
				UserMessage jMsg = allMessages.get(j);
				boolean receiversEq = iMsg.getRecipientId() == jMsg.getRecipientId();
				boolean sendersEq = iMsg.getSenderId() == jMsg.getSenderId();
				boolean sendEqRec = iMsg.getSenderId() == jMsg.getRecipientId();
				boolean recEqSend = iMsg.getRecipientId() == jMsg.getSenderId();
				
				if((receiversEq && sendersEq) || (sendEqRec && recEqSend)) {
					eqCount++;
					if(iMsg.emailCompare(jMsg) != 0) {
						fail("Messages should be equal by email comparison:\n\t1: " + 
								iMsg + "\n\t2: " + jMsg);	
					}
				}
				else {
					if(iMsg.emailCompare(jMsg) == 0) {
						fail("Messages should not be equal by email comparison:\n\t1: " + 
								iMsg + "\n\t2: " + jMsg);	
					}
				}
			}
		}

		if(eqCount < 1000) {
			initialize();
			testEmailCompare();
		}
	}
	
	@Test
	public void testCompareTo() {
		Collections.sort(allMessages);
		UserMessage prevMsg = null;
		ArrayList<UserMessage> prevConversations = new ArrayList<UserMessage>();
		for(UserMessage uMsg : allMessages) {
			if(prevMsg == null) {
				prevMsg = uMsg;
			}
			else {
				if(uMsg.emailCompare(prevMsg) == 0) {
					assert(prevMsg.compareTo(uMsg) < 0);
				} 
				else {
					assert(prevMsg.compareTo(uMsg) < 0);
					for(int i = 0; i < prevConversations.size(); i++) {
						if(prevMsg.emailCompare(prevConversations.get(i)) == 0) {
							fail("Messages were not sorted properly, messages corresponding to "
									+ "a conversation side should be grouped together");	
						}
					}
					prevConversations.add(uMsg);
				}
			}
		}
	}
	
	private void createUsers() {
		for(int i = 0; i < userCount; i++) {
			String name = TestUtilities.getRandomString(rnd.nextInt(15));
			String email = TestUtilities.getRandomString(rnd.nextInt(15)) + i;
			long phoneNumber = 5558675309l;
			Permission permission = null;
			String password = TestUtilities.getRandomString(rnd.nextInt(15));
			int lastLocation = 0;
			byte[] photo = new byte[0];
			
			users.add(new User(i, name, email, phoneNumber, permission, password, lastLocation, photo));
		}
	}
	
	private void createMessages() {
		
		for(int i = 0; i < 1000; i++) {
			long sender = rnd.nextInt(userCount);
			long receiver = rnd.nextInt(userCount);
			String msg = "";
			LocalDateTime dateTime = TestUtilities.getRandomLocalDateTime();
			
			UserMessage usrMsg = new UserMessage(i, sender, receiver, msg, dateTime);
			allMessages.add(usrMsg);
			if((sender == 0 && receiver == 1) ||
				(receiver == 0 && sender == 1)){
				oneConversation.add(usrMsg);
			}
		
		}
	}
}
