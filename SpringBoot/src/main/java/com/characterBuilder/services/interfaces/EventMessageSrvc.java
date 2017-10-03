package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.EventMessage;
import com.characterBuilder.entities.User;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;

public interface EventMessageSrvc
{
	public List<EventMessage> getByUser(User user);
	
	public void add(EventMessage eventMsg) throws ExceedingLimitException;
	
	public void delete(EventMessage eventMsg);
	public void deleteOldMsgs(long eventId);
}
