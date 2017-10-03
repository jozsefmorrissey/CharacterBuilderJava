package com.characterBuilder.services.simple;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.EventMessage;
import com.characterBuilder.entities.User;
import com.characterBuilder.markers.Message;
import com.characterBuilder.repositories.EventMessageRepo;
import com.characterBuilder.services.interfaces.EventMessageSrvc;
import com.characterBuilder.services.interfaces.EventSrvc;
import com.characterBuilder.services.interfaces.ParticipantSrvc;
import com.characterBuilder.services.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.PropertiesUtil;
import com.characterBuilder.util.TestUtilities;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventMessageSrvcTest
{
	@Autowired
	EventMessageSrvc eventMsgSrvc;
	
	@Autowired
	EventMessageRepo eventMsgRepo;
	
	@Autowired
	EventSrvc eventSrvc;
	
	@Autowired
	UserSrvc userSrvc;
	
	@Autowired
	ParticipantSrvc partSrvc;
	
	
	User user1;
	User user2;
	User user3;
	User user10;
	
	@Before
	public void initialize()
	{
		user1 = userSrvc.getById(1);
		user2 = userSrvc.getById(2);
		user3 = userSrvc.getById(3);
		user10 = userSrvc.getById(10);
	}

	@Test
	public void testGetByUser()
	{
	  List<EventMessage> msgs = eventMsgSrvc.getByUser(user1);
	  assert (msgs.size() == 5);
	  findRecipients(msgs, 3);
	  msgs = eventMsgSrvc.getByUser(user2);
	  assert (msgs.size() == 3);
	  findRecipients(msgs);
	  msgs = eventMsgSrvc.getByUser(user3);
	  assert (msgs.size() == 4);
	  findRecipients(msgs, 3);

	}

	@SuppressWarnings({ "unchecked" })
	private void findRecipients(List<EventMessage> messages, int... recIds)
	{
	  List<Message> msgs = (List<Message>)(List<?>)messages;
	  ArrayList<Boolean> found = new ArrayList<Boolean>();
	  long id = TestUtilities.findRecipients(msgs, found, recIds);
	  if(id != -1)
	  {
	    fail("An Id was found that was not expected: " + id);
	  }
	  
	  int loc = TestUtilities.allTrue(found);
	  if(loc != -1)
	  {
	    fail("An Id was not found: " + recIds[loc]);
	  }
	}
	
	@Test
	public void testCountControl()
	{
		double maxCount = (double)PropertiesUtil.eventMessageCountMax();
		double overflowFactor = PropertiesUtil.eventMessageOverflowFactor();
		int tolerance = (int)(maxCount * overflowFactor);

		long count = eventMsgRepo.countByEventId(4l);
		long lastCount = count;
		boolean removedExcess = false;
		boolean reachedLimit = false;
		
		while(!removedExcess) {
			if(lastCount > count)
				removedExcess = true;
			if(count == tolerance)
				reachedLimit = true;
			
			if(count > tolerance)
				fail("EventMessage count exceeded the given limit of " + 
						tolerance + " by " + (count - tolerance)); 
			
			addMsgs(1);
			
			lastCount = count;
			count = eventMsgRepo.countByEventId(4l);
		}
		
		if(!reachedLimit)
			fail("Messages were prematurely removed");
	}
	
	@Test 
	public void testDeleteOldMsgs() {
		Event event = eventSrvc.getById(4);
		
		int max = PropertiesUtil.eventMessageCountMax();
		double overflowFactor = PropertiesUtil.eventMessageOverflowFactor();
		int threashHold = (int)((double)(max * overflowFactor) * .75);
		List<EventMessage> allMsgs = eventMsgSrvc.getByUser(event.getPoster());
		
		while(allMsgs.size() < threashHold) {
			addMsgs(threashHold/6);
			allMsgs = eventMsgSrvc.getByUser(event.getPoster());
		}
		
		eventMsgSrvc.deleteOldMsgs(event.getId());
		List<EventMessage> saved = eventMsgSrvc.getByUser(event.getPoster());
		
		verifyMostReasentSaved(allMsgs, saved);
	}
	
	@Test
	public void exceedLimitTest() {
		int msgLengthMax = PropertiesUtil.eventMessageLengthMax();		
		String msg = TestUtilities.getRandomString(msgLengthMax + 1);
		try
		{
			EventMessage eventMsg;
			LocalDateTime ldt = TestUtilities.getRandomLocalDateTime();
			int userId = TestUtilities.getInt(10, 16);
			
			eventMsg = new EventMessage(0, userId, 4, msg, ldt);
			eventMsgSrvc.add(eventMsg);
			fail("This message should have exceeded length limit: length = " + msg.length() + "\n\t" + msg);
		} catch (ExceedingLimitException e)
		{
		}
	}
	
	public void verifyMostReasentSaved(List<EventMessage> allMsgs, List<EventMessage> saved) {
		for (EventMessage aMsg : allMsgs)
		{
			if (!saved.contains(aMsg))
			{
				for (EventMessage sMsg : saved)
				{
					boolean isOlder = aMsg.getDateTime().compareTo(sMsg.getDateTime()) > 0;
					if (!isOlder)
					{
						fail("Newer message deleted, while older message saved:\n\tOlder/saved:   " +
						sMsg + "\n\tNewer/deleted: " + aMsg);
					}
				}
			}
		}
	}
	
	private ArrayList<EventMessage> addMax()
	{
		int max = PropertiesUtil.eventMessageCountMax();
		return addMsgs(max);
	}
	
	private ArrayList<EventMessage> addMsgs(int count)
	{
		ArrayList<EventMessage> msgs = new ArrayList<EventMessage>();
		int msgLengthMax = PropertiesUtil.eventMessageLengthMax();		
		
		for(int i = 0; i < count; i++) {
			String msg = TestUtilities.getRandomString(msgLengthMax);
			try
			{
				EventMessage eventMsg;
				LocalDateTime ldt = TestUtilities.getRandomLocalDateTime();
				int userId = TestUtilities.getInt(10, 16);
				
				eventMsg = new EventMessage(0, userId, 4, msg, ldt);
				eventMsgSrvc.add(eventMsg);
			} catch (ExceedingLimitException e)
			{
				fail("This message should not have exceeded length limit: length = " + msg.length() + "\n\t" + msg);
			}
			
		}
		return msgs;
	}
}
