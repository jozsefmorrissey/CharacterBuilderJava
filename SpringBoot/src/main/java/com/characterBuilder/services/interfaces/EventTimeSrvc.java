package com.characterBuilder.services.interfaces;

import java.util.Collection;
import java.util.List;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;

public interface EventTimeSrvc {
	public List<EventTime> getTimesByEvent(Event event);
	public void addTime(EventTime eventTime) throws ExceedingLimitException;
	public void addAllTimes(Collection<EventTime> eventTimes) throws ExceedingLimitException;
	public void deleteTime(EventTime eventTime);
	public void deleteAllTimes(Collection<EventTime> eventTimes);
	public void deleteAllTimes(Event event);
}
