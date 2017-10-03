package com.characterBuilder.services.simple;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.UserMessage;
import com.characterBuilder.services.interfaces.UserMessageSrvc;
import com.characterBuilder.services.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.throwable.exceptions.InvalidIdException;
import com.characterBuilder.throwable.exceptions.NullOrEmptyStringException;
import com.characterBuilder.util.PropertiesUtil;
import com.characterBuilder.util.TestUtilities;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMessageSrvcTest
{

	@Autowired
	UserMessageSrvc userMsgSrvc;

	@Autowired
	UserSrvc userSrvc;

	private User user3;
	private User user4;

	Random rnd = new Random();

	@Before
	public void initialize()
	{
		user3 = userSrvc.getById(3L);
		user4 = userSrvc.getById(4L);
	}

	@Test
	public void testGetByUser()
	{
		User user = userSrvc.getById(1L);
		List<UserMessage> msgs = userMsgSrvc.getByUser(user);
		Collections.sort(msgs);
		UserMessage prev = null;
		for (UserMessage msg : msgs)
		{
			System.out.println(msg.getMessage());
			if (prev != null)
			{
				assert (prev.getDateTime().compareTo(msg.getDateTime()) <= 0);
			}
			prev = msg;
		}
	}

	@Test
	public void test()
	{
		addAndVerify();
		addAndVerify();
		addAndVerify();

		double overFlowFactor = PropertiesUtil.messageOverflowFactor();
		int maxMsgCount = PropertiesUtil.messageCountMax();
		int maxMsgsAllowed = (int) (overFlowFactor * maxMsgCount);

		assert (userMsgSrvc.getByUser(user3).size() <= maxMsgsAllowed);
		assert (userMsgSrvc.getByUser(user4).size() <= maxMsgsAllowed);
	}
	
	@Test
	public void edgeCases()
	{
		UserMessage[] userMessages = new UserMessage[]{
		/*0*/	null,
		/*1*/	new UserMessage(0, 3, 4, "", null),
		/*2*/	new UserMessage(0, 3, 4, null, null),
		/*3*/	new UserMessage(0, 10000000l, 4, "hello world", null),
		/*4*/	new UserMessage(0, 3, 10000000l, "hello world", null),
		/*5*/	new UserMessage(0, 0, 4, "hello world", null),
		/*6*/	new UserMessage(0, 3, 0, "hello world", null),
		/*7*/	new UserMessage(0, 3, 4, TestUtilities.getRandomString(249), null),
		};
		
		for(int i = 0; i < userMessages.length; i++)
		{
			try
			{			
				userMsgSrvc.add(userMessages[i]);
			} catch (NullPointerException e)
			{
				if(i != 0)
					fail("Unexpected null pointer was thrown");
			} catch (ExceedingLimitException e)
			{
				if(i != 7)
					fail("limit was exceeded when not expected.");
							
			} catch (NullOrEmptyStringException e)
			{
				if(i != 1 && i != 2)
					fail("Message field was indicated to be null or empty when not expected");
			} catch (InvalidIdException e)
			{
				List<Integer> positions = Arrays.asList(new Integer[]{3,4,5,6});
				if(!positions.contains(new Integer(i)))
					fail("An invalid id was detected when not expected.");
			}
		}
	}

	private void addAndVerify()
	{
		List<UserMessage> allMsgs3 = userMsgSrvc.getByUser(user3);
		List<UserMessage> allMsgs4 = userMsgSrvc.getByUser(user4);

		List<UserMessage> createdMsgs = addMax();

		allMsgs3.addAll(createdMsgs);
		allMsgs4.addAll(createdMsgs);

		assert (verifyMostReasentSaved(user3));
		assert (verifyMostReasentSaved(user4));
	}

	private boolean verifyMostReasentSaved(User user)
	{
		
		int max = PropertiesUtil.messageCountMax();
		double overflowFactor = PropertiesUtil.messageOverflowFactor();
		int threashHold = (int)((double)(max * overflowFactor) * .75);
		List<UserMessage> allMsgs4 = userMsgSrvc.getByUser(user4);
		
		while(allMsgs4.size() < threashHold) {
			addMsgs(threashHold/6);
			allMsgs4 = userMsgSrvc.getByUser(user4);
		}
		
		userMsgSrvc.deleteOldMsgs(user.getId());
		List<UserMessage> currMsgs4 = userMsgSrvc.getByUser(user4);

		for (UserMessage ahdt : allMsgs4)
		{
			if (!currMsgs4.contains(ahdt))
			{
				for (UserMessage sHdt : currMsgs4)
				{
					boolean isSameConversation = ahdt.emailCompare(sHdt) == 0;
					boolean isOlder = ahdt.getDateTime().compareTo(sHdt.getDateTime()) > 0;
					if (isSameConversation && !isOlder)
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	private ArrayList<UserMessage> addMax()
	{
		int max = PropertiesUtil.messageCountMax();
		return addMsgs(max);
	}
	
	private ArrayList<UserMessage> addMsgs(int count)
	{
		ArrayList<UserMessage> msgs = new ArrayList<UserMessage>();

		for (int i = 0; i < count; i++)
		{
			try
			{
				UserMessage uMsg;
				UserMessage msg45;
				String msg = TestUtilities.getRandomString(248);
				LocalDateTime ldt = TestUtilities.getRandomLocalDateTime();
				if (Math.random() < .5) {
					uMsg = new UserMessage(0, 3, 4, msg, ldt);
					msg45 = new UserMessage(0, 5, 4, msg, ldt);
				}
				else {
					uMsg = new UserMessage(0, 4, 3, msg, ldt);
					msg45 = new UserMessage(0, 5, 4, msg, ldt);
				}
				
				msgs.add(userMsgSrvc.addDoNotExpose(uMsg));
				msgs.add(userMsgSrvc.addDoNotExpose(msg45));
			} catch (ExceedingLimitException | NullOrEmptyStringException | InvalidIdException e)
			{
			}
		}
		return msgs;
	}
}
