package com.characterBuilder.services.simple;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.services.interfaces.EventTimeSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.PropertiesUtil;
import com.characterBuilder.util.StringUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTimeSrvcTest {

	@Autowired
	EventTimeSrvc eventTimeSrvc;
	
	private long eventId = 3L;
	private Event event1;
	private Event event3;
	
	@Before
	public void initialize() {
		event1 = new Event();
		event1.setId(1);
		event3 = new Event();
		event3.setId(3);
	}

	@Test
	public void testGetTimes() {
		List<EventTime> eventTime = eventTimeSrvc.getTimesByEvent(event1);
		String str = "2002-09-10T14:10:10.123";
		LocalDateTime dateTime = StringUtil.localDateTimeConverter(str);
		assertEquals(eventTime.get(0), dateTime);
	}
	
	@Test
	public void testAddTime() throws ExceedingLimitException {
		EventTime et = GetRandomEventTime();
		eventTimeSrvc.addTime(et);
		
		List<EventTime> times = eventTimeSrvc.getTimesByEvent(event3);
		assertEquals(et, times.get(0).getDateTime());
		
		eventTimeSrvc.deleteTime(et);
	}
	
	@Test
	public void testAddTimes() {
		List<EventTime> eventTimes = (List<EventTime>) GetRandomEventTimes();
		try {
			eventTimeSrvc.addAllTimes(eventTimes);
		} catch (ExceedingLimitException e) {
			assert(false);
		}
		
		// Check Consistancy
		List<EventTime> dbTimes = eventTimeSrvc.getTimesByEvent(event3); 
		if(dbTimes.size() != eventTimes.size())
			assert(false);
		Collections.sort(eventTimes);
		for(int index = 0; index < dbTimes.size(); index++) {
			LocalDateTime dbT = dbTimes.get(index).getDateTime();
			LocalDateTime etT = eventTimes.get(index).getDateTime();
			if(!dbT.equals(etT))
				assert(false);
		}
		
		//Push over limit
		eventTimes = new ArrayList<EventTime>();
		eventTimes.add(GetRandomEventTime());
		try {
			eventTimeSrvc.addAllTimes(eventTimes);
			assert(false);
		} catch (ExceedingLimitException e) {
			assert(true);
		}
		eventTimeSrvc.deleteAllTimes(new Event(eventId, null, null, dbTimes, null, null));
	}

	private void assertEquals(EventTime eventTime, LocalDateTime ltd) {
		LocalDateTime et = eventTime.getDateTime();
		assert(ltd.equals(et));
	}
	
	private Collection<EventTime> GetRandomEventTimes() {
		Collection<EventTime> times = new ArrayList<EventTime>();
		int max = PropertiesUtil.eventTimeCountMax();
		for(int count = 0; count < max; count++) {
			times.add(GetRandomEventTime());
		}
		return times;
	}
	
	private EventTime GetRandomEventTime() {
		return new EventTime(0, eventId, getRandomDate());
	}
	
	private LocalDateTime getRandomDate() {
		LocalDateTime d = LocalDateTime.now();
		long multiplier = 1000000000;
		long s = (int)(Math.random() * multiplier - multiplier/2);
		d = d.plusSeconds(s);
		int halfHour = Math.random() > .5 ? 0 : 30;
		d = d.minusMinutes(d.getMinute() + halfHour);
		d = d.minusSeconds(d.getSecond());
		d = d.minusNanos(d.getNano());
		
		return d;
	}
}
