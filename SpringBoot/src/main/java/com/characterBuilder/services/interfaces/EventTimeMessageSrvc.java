package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.EventTimeMessage;
import com.characterBuilder.entities.User;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;

public interface EventTimeMessageSrvc
{	
	public List<EventTimeMessage> getByUser(User user);
	
	public void add(EventTimeMessage eventMsg) throws ExceedingLimitException;
	
	public void delete(EventTimeMessage eventMsg);
	public void deleteOldMsgs(long eventTimeId);
}
