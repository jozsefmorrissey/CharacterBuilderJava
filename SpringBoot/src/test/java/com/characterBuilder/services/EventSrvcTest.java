package com.characterBuilder.services;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.services.interfaces.EventSrvc;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegistered;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventSrvcTest {
	
	@Autowired
	private EventSrvc eventSrvc;
	
	private long id;
	private Event event;
	
	@Before
	public void testEventSrvc() {
		long id = 0;
		User posterId = new User();
		posterId.setId(1);
		String title = "basket Ball";
		Set<EventTime> times = null;
		Set<EventImage> images = null;
		String description = null;
		
		event = new Event(id, title, posterId, times, images, description);
	}

	@Test
	@Transactional
	public void testGetters() {
		try {
			eventSrvc.add(event);
		} catch (EmailAlreadyRegistered e) {
			assert(false);
		}
		testGetAllEvents(2);
		
		eventSrvc.delete(event);
		
		testGetAllEvents(1);
	}
	
	@Test
	@Transactional
	public void testUpdate() {
		event = this.testGetEvent(3);
		String newTitle = "Bar Fight";
		event.setTitle(newTitle);;
		eventSrvc.update(event);
		event = this.testGetEvent(id);
		assert(event.getTitle().equals(newTitle));
	}


	@Test
	@Transactional
	public void testDelete() {
		event = this.testGetEvent(event.getId());
		
		eventSrvc.delete(event);
		try {
			eventSrvc.add(event);
			assert(true);
		} catch (EmailAlreadyRegistered e) {
			assert(false);
		}
		eventSrvc.delete(event);
	}

	@Test
	@Transactional
	public void testAdd() {
		try {
			eventSrvc.add(event);
			assert(true);
		} catch (EmailAlreadyRegistered e) {
			assert(false);
		}
		try {
			eventSrvc.add(event);
			assert(false);
		} catch (EmailAlreadyRegistered e) {
			assert(true);
		}
	}


	public Event testGetEvent(long id) {
		Event ret = eventSrvc.getById(id);
		assert(ret.getId() == id);
		return ret;
	}


	public void testGetAllEvents(int size) {
		Set<Event> events = eventSrvc.getAll();
		assert(events.size() == size);
	}
}
