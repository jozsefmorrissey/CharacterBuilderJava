package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.User;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegisteredException;

public interface EventSrvc {
	public Event getById(long id);
	public void add(Event event) throws EmailAlreadyRegisteredException;
	public void delete(Event event);
	public void update(Event event);
	public List<Event> getAll();
	List<Event> getAllByPoster(User poster);

}
