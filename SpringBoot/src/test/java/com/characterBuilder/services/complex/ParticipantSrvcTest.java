package com.characterBuilder.services.complex;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.Participant;
import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.services.interfaces.ParticipantSrvc;
import com.characterBuilder.services.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.AddingConflictingIds;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.throwable.exceptions.ZeroIdException;
import com.characterBuilder.util.PropertiesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticipantSrvcTest {
	
	@Autowired
	private ParticipantSrvc partSrvc;
	
	@Autowired
	private UserSrvc userSrvc;
		
	private User eventPoster;
	private EventTime eventTime1;
	private EventTime eventTime2;
	private List<Participant> participants;
	
	@Before
	public void initialize() {
		eventPoster = userSrvc.getByEmail("jozsef.morrissey@gmail.com");
		participants = new ArrayList<Participant>();

		eventTime1 = new EventTime();
		eventTime1.setId(1L);

		eventTime2 = new EventTime();
		eventTime2.setId(2L);
		
		randomizeMaxParticipants();		 
	}
	
	@Test
	public void testAdd() {
		if(partSrvc.getParticipants(eventTime2.getId()).size() != 0)
			fail("Database should be clear of participants connected to an EventTime with the Id of 2.");
		
		try {
			partSrvc.addParticipant(participants.get(0));
		} catch (ExceedingLimitException | ZeroIdException e1) {
			fail("No error should be thrown");
		}
		assert(partSrvc.getParticipants(eventTime2.getId()).size() == 1);
	
		try {
			partSrvc.addParticipant(participants.get(1));
		} catch (ExceedingLimitException | ZeroIdException e) {
			fail("No error should be thrown");
		}
		assert(partSrvc.getParticipants(eventTime2.getId()).size() == 2);		
		
		partSrvc.removeParticipant(participants.get(0));
		partSrvc.removeParticipant(participants.get(1));
		assert(partSrvc.getParticipants(eventTime2.getId()).size() == 0);
	}
	
	@Test
	public void testAddAll() {
		if(partSrvc.getParticipants(eventTime2.getId()).size() != 0)
			fail("Database should be clear of participants connected to an EventTime with the Id of 2.");
		try {
			partSrvc.addAllParticipants(participants);
		} catch (ExceedingLimitException | AddingConflictingIds | ZeroIdException e) {
			fail("No Exceptions should not be thrown");
		}
		
		partSrvc.removeAllParticipants(participants);
		assert(partSrvc.getParticipants(eventTime2.getId()).size() == 0);
	}
	
	@Test
	public void testExceedLimit() {
		if(partSrvc.getParticipants(eventTime2.getId()).size() != 0)
			fail("Database should be clear of participants connected to an EventTime with the Id of 2.");
		try {
			partSrvc.addAllParticipants(participants);
		} catch (ExceedingLimitException | AddingConflictingIds | ZeroIdException e) {
			fail("No Exceptions should not be thrown");
		}
		
		try {
			partSrvc.addAllParticipants(participants);
			fail("This addition should exceed the allowed limit.");
		} catch (AddingConflictingIds | ZeroIdException e) {
			fail("AddingConflictingIds and ZeroIdException should not be thrown");
		} catch (ExceedingLimitException e) {
			assert(true);
		}
		
		try {
			partSrvc.addParticipant(participants.iterator().next());
			fail("This addition should exceed the allowed limit.");
		} catch (ZeroIdException e) {
			fail("ZeroIdException should not be thrown");
		} catch (ExceedingLimitException e) {
			assert(true);
		}
		
		partSrvc.removeAllParticipants(participants);
		assert(partSrvc.getParticipants(eventTime2.getId()).size() == 0);
	}
	
	@Test
	public void testGet() {
		List<Participant> parts = partSrvc.getParticipants(eventTime1.getId());
		assert(parts.size() == 6);
	}
	
	@Test
	public void testAddZeroIds() {
		Participant p= participants.get(0);
		
		p.getParticipant().setId(0);
		try {
			partSrvc.addParticipant(p);
			fail("Add should not be successful");
		} catch (ExceedingLimitException e) {
			fail("ExceedingLimitException should not be thrown");
		} catch (ZeroIdException e) {
			assert(true);
		}
		
		p.getParticipant().setId(1);
		p.setEventTimeId(0);
		try {
			partSrvc.addParticipant(p);
			fail("Add should not be successful");
		} catch (ExceedingLimitException e) {
			fail("ExceedingLimitException should not be thrown");
		} catch (ZeroIdException e) {
			assert(true);
		}
	}
	
	@Test
	public void testAddAllZeroIds() {
		int userIndex = (int)Math.random()*participants.size();
		int eventTimeIndex = (int)Math.random()*participants.size();
		
		participants.get(eventTimeIndex).setEventTimeId(0);
		try {
			partSrvc.addAllParticipants(participants);
			fail("Add should not be successful");
		} catch (ExceedingLimitException | AddingConflictingIds e) {
			fail("ExceedingLimitException and AddingConflictgIds should not be thrown");
		} catch (ZeroIdException e) {
			assert(true);
		}
		
		participants.get(eventTimeIndex).setEventTimeId(1);
		participants.get(userIndex).getParticipant().setId(0);
		try {
			partSrvc.addAllParticipants(participants);
			fail("Add should not be successful");
		} catch (ExceedingLimitException | AddingConflictingIds e) {
			fail("ExceedingLimitException and AddingConflictgIds should not be thrown");
		} catch (ZeroIdException e) {
			assert(true);
		}
	}
	
	@Test
	public void testConflictingIds() {
		int conflictIndex = (int)Math.random()*participants.size();
		
		participants.get(conflictIndex).setEventTimeId(1);
		try {
			partSrvc.addAllParticipants(participants);
			fail("Add should not be successful");
		} catch (ExceedingLimitException | ZeroIdException e) {
			fail("ExceedingLimitException and ZeroIdException should not be thrown");
		} catch (AddingConflictingIds e) {
			assert(true);
		}
	}
	
	private void randomizeMaxParticipants() {
		List<User> users = new ArrayList<User>();
		users.addAll(userSrvc.getAll());
		users.remove(eventPoster);
		Collections.shuffle(users);
		int max = PropertiesUtil.participantMax();
		for(int index = 0; index < max; index++) {
			this.participants.add(new Participant(0, 2, users.get(index)));
		}
	}
}
