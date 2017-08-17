package com.characterBuilder.services.interfaces;

import java.util.Set;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegistered;

public interface EventSrvc {
	public Event getById(long id);
	public void add(Event event) throws EmailAlreadyRegistered;
	public void delete(Event event);
	public void update(Event event);
	public Set<Event> getAll();
	
	public Set<EventTime> getEventTimes(long id);
	public Set<EventImage> getEventImages(long id);
}
