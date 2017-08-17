package com.characterBuilder.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.repositories.EventTimeRepo;
import com.characterBuilder.services.interfaces.EventTimeSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.PropertiesUtil;

@Service
public class EventTimeSrvcImpl implements EventTimeSrvc{

	@Autowired
	EventTimeRepo eventTimeRepo;
	@SuppressWarnings("unchecked")
	@Override
	public List<EventTime> getTimesByEvent(Event event) {
		List<EventTime> eventTimes = eventTimeRepo.getByEventId(event.getId());
		Collections.sort(eventTimes);
		return eventTimes;
	}

	@Override
	public void addTime(EventTime eventTime) throws ExceedingLimitException {
		checkCount(eventTime.getEventId(), 1);
		eventTimeRepo.saveAndFlush(eventTime);
	}

	@Override
	public void addAllTimes(Collection<EventTime> eventTimes) throws ExceedingLimitException {
		if(eventTimes.size() == 0)
			return;
		checkCount(((List<EventTime>)eventTimes).get(0).getEventId(), eventTimes.size());
		eventTimeRepo.save(eventTimes);
	}

	@Override
	public void deleteTime(EventTime eventTime) {
		eventTimeRepo.delete(eventTime);
	}

	@Override
	public void deleteAllTimes(Collection<EventTime> eventTimes) {
		eventTimeRepo.delete(eventTimes);
	}

	@Override
	public void deleteAllTimes(Event event) {
		eventTimeRepo.deleteByEventId(event.getId());
	}
	
	private void checkCount(long eventId, int added) throws ExceedingLimitException {
		int max = PropertiesUtil.getMaxEventTimeCount();
		int dbCount = eventTimeRepo.getByEventId(eventId).size();
		if(max < dbCount + added)
			throw new ExceedingLimitException(dbCount + added, max, "Event Times");
	}
}
