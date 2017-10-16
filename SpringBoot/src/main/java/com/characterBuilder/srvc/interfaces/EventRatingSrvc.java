package com.characterBuilder.srvc.interfaces;

import java.util.Collection;
import java.util.List;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.EventRating;
import com.characterBuilder.throwable.exceptions.TooCloseException;

public interface EventRatingSrvc {
	public List<EventRating> getByReciever(Event reciever);
	public List<EventRating> getByAttributer(User attributer);
	public List<EventRating> getByAttributerAndReciever(User attributer, Event reciever);
	
	public void addRating(EventRating rating) throws TooCloseException;
	void addAllRatings(Collection<EventRating> ratings) throws TooCloseException;
	
	public void deleteRating(EventRating rating);
	void deleteAllRatings(Collection<EventRating> ratings);
}
