package com.characterBuilder.services.simple;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.srvc.interfaces.EventSrvc;
import com.characterBuilder.srvc.interfaces.UserSrvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventSrvcTest {
	
	@Autowired
	private EventSrvc eventSrvc;
	
	@Autowired UserSrvc userSrvc;
	
	private long id;
	private Event event;
	private User poster;
	private String[] titles = new String[]{"Debate", "Code Chalange", "Dance Off(Pants Off)", "Stranger Danger Convention"};
	private List<Event> events = new ArrayList<Event>();
	
	@Before
	public void testEventSrvc() {
		long id = 0;
		poster = userSrvc.getById(1);
		String title = "basket Ball";
		List<EventTime> times = new ArrayList<EventTime>();
		List<EventImage> images = new ArrayList<EventImage>();
		String description = null;
		
		event = new Event(id, title, poster, times, images, description);
	
		for(int i = 0; i < titles.length; i++) {
			events.add(new Event(i + 1, titles[i], poster, times, images, ""));
		}
	}
	
	@Test
	public void testGetAll() {
		List<Event> eventsDb = eventSrvc.getAll();
		verifyEqual(eventsDb);
	}
	
	@Test
	public void testGetAllByPoster() {
		List<Event> eventsDb = eventSrvc.getAllByPoster(poster);
		verifyEqual(eventsDb);
	}

	private void verifyEqual(List<Event> eventsDb) {
		for(Event e1 : eventsDb) {
			boolean found = false;
			for(Event e2 : events) {
				if(e1.getId() == e2.getId() && e1.getTitle().equals(e2.getTitle())) {
					found = true;
				}
			System.out.println(e2);
			}
			assert(found);
		}
	}
	

//	@Test
//	@Transactional
//	public void testGetters() {
//		int dbCount = eventSrvc.getAll()
//		try {
//			eventSrvc.add(event);
//		} catch (EmailAlreadyRegisteredException e) {
//			assert(false);
//		}
//		testGetAllEvents(2);
//		
//		eventSrvc.delete(event);
//		
//		testGetAllEvents(1);
//	}
//	
//	@Test
//	@Transactional
//	public void testUpdate() {
//		event = this.testGetEvent(3);
//		String newTitle = "Bar Fight";
//		event.setTitle(newTitle);;
//		eventSrvc.update(event);
//		event = this.testGetEvent(id);
//		assert(event.getTitle().equals(newTitle));
//	}
//
//
//	@Test
//	@Transactional
//	public void testDelete() {
//		event = this.testGetEvent(event.getId());
//		
//		eventSrvc.delete(event);
//		try {
//			eventSrvc.add(event);
//			assert(true);
//		} catch (EmailAlreadyRegisteredException e) {
//			assert(false);
//		}
//		eventSrvc.delete(event);
//	}
//
//	@Test
//	@Transactional
//	public void testAdd() {
//		try {
//			eventSrvc.add(event);
//			assert(true);
//		} catch (EmailAlreadyRegisteredException e) {
//			assert(false);
//		}
//		try {
//			eventSrvc.add(event);
//			assert(false);
//		} catch (EmailAlreadyRegisteredException e) {
//			assert(true);
//		}
//	}
//
//
//	public Event testGetEvent(long id) {
//		Event ret = eventSrvc.getById(id);
//		assert(ret.getId() == id);
//		return ret;
//	}


	public void testGetAllEvents(int size) {
		List<Event> events = eventSrvc.getAll();
		assert(events.size() == size);
	}
}
