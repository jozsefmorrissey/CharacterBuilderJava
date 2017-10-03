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
import com.characterBuilder.entities.EventTimeMessage;
import com.characterBuilder.entities.User;
import com.characterBuilder.markers.Message;
import com.characterBuilder.repositories.EventTimeMessageRepo;
import com.characterBuilder.services.interfaces.EventSrvc;
import com.characterBuilder.services.interfaces.EventTimeMessageSrvc;
import com.characterBuilder.services.interfaces.ParticipantSrvc;
import com.characterBuilder.services.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.PropertiesUtil;
import com.characterBuilder.util.TestUtilities;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTimeMessageTest
{
	@Autowired
	EventTimeMessageSrvc eventTimeMsgSrvc;

	@Autowired
	EventTimeMessageRepo eventTimeMsgRepo;

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
		List<EventTimeMessage> msgs = eventTimeMsgSrvc.getByUser(user1);
		assert (msgs.size() == 5);
		findRecipients(msgs, 3);
		msgs = eventTimeMsgSrvc.getByUser(user2);
		assert (msgs.size() == 3);
		findRecipients(msgs);
		msgs = eventTimeMsgSrvc.getByUser(user3);
		assert (msgs.size() == 4);
		findRecipients(msgs, 3);

	}

	@SuppressWarnings({ "unchecked" })
	private void findRecipients(List<EventTimeMessage> messages, int... recIds)
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
		double maxCount = (double) PropertiesUtil.eventTimeMessageCountMax();
		double overflowFactor = PropertiesUtil.eventTimeMessageOverflowFactor();
		int tolerance = (int) (maxCount * overflowFactor);

		long count = eventTimeMsgRepo.countByEventTimeId(4l);
		long lastCount = count;
		boolean removedExcess = false;
		boolean reachedLimit = false;

		while (!removedExcess)
		{
			if (lastCount > count)
				removedExcess = true;
			if (count == tolerance)
				reachedLimit = true;

			if (count > tolerance)
				fail("EventTimeMessage count exceeded the given limit of " + tolerance + " by " + (count - tolerance));

			addMsgs(1);

			lastCount = count;
			count = eventTimeMsgRepo.countByEventTimeId(4l);
		}

		if (!reachedLimit)
			fail("Messages were prematurely removed");
	}

	@Test
	public void testDeleteOldMsgs()
	{
		Event event = eventSrvc.getById(4);

		int max = PropertiesUtil.eventTimeMessageCountMax();
		double overflowFactor = PropertiesUtil.eventTimeMessageOverflowFactor();
		int threashHold = (int) ((double) (max * overflowFactor) * .75);
		List<EventTimeMessage> allMsgs = eventTimeMsgSrvc.getByUser(event.getPoster());

		while (allMsgs.size() < threashHold)
		{
			addMsgs(threashHold / 6);
			allMsgs = eventTimeMsgSrvc.getByUser(event.getPoster());
		}

		eventTimeMsgSrvc.deleteOldMsgs(event.getId());
		List<EventTimeMessage> saved = eventTimeMsgSrvc.getByUser(event.getPoster());

		verifyMostReasentSaved(allMsgs, saved);
	}

	@Test
	public void exceedLimitTest()
	{
		int msgLengthMax = PropertiesUtil.eventTimeMessageLengthMax();
		String msg = TestUtilities.getRandomString(msgLengthMax + 1);
		try
		{
			EventTimeMessage eventTimeMsg;
			LocalDateTime ldt = TestUtilities.getRandomLocalDateTime();
			int userId = TestUtilities.getInt(10, 16);

			eventTimeMsg = new EventTimeMessage(0, userId, 4, msg, ldt);
			eventTimeMsgSrvc.add(eventTimeMsg);
			fail("This message should have exceeded length limit: length = " + msg.length() + "\n\t" + msg);
		} catch (ExceedingLimitException e)
		{
		}
	}

	public void verifyMostReasentSaved(List<EventTimeMessage> allMsgs, List<EventTimeMessage> saved)
	{
		for (EventTimeMessage aMsg : allMsgs)
		{
			if (!saved.contains(aMsg))
			{
				for (EventTimeMessage sMsg : saved)
				{
					boolean isOlder = aMsg.getDateTime().compareTo(sMsg.getDateTime()) > 0;
					if (!isOlder)
					{
						fail("Newer message deleted, while older message saved:\n\tOlder/saved:   " + sMsg
								+ "\n\tNewer/deleted: " + aMsg);
					}
				}
			}
		}
	}

	private ArrayList<EventTimeMessage> addMsgs(int count)
	{
		ArrayList<EventTimeMessage> msgs = new ArrayList<EventTimeMessage>();
		int msgLengthMax = PropertiesUtil.eventTimeMessageLengthMax();

		for (int i = 0; i < count; i++)
		{
			String msg = TestUtilities.getRandomString(msgLengthMax);
			try
			{
				EventTimeMessage eventTimeMsg;
				LocalDateTime ldt = TestUtilities.getRandomLocalDateTime();
				int userId = TestUtilities.getInt(10, 16);

				eventTimeMsg = new EventTimeMessage(0, userId, 4, msg, ldt);
				eventTimeMsgSrvc.add(eventTimeMsg);
			} catch (ExceedingLimitException e)
			{
				fail("This message should not have exceeded length limit: length = " + msg.length() + "\n\t" + msg);
			}

		}
		return msgs;
	}
}
