package com.characterBuilder.srvc.impl;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.comparator.ComparitorDateTime;
import com.characterBuilder.entities.EventMessage;
import com.characterBuilder.entities.User;
import com.characterBuilder.repo.EventMessageRepo;
import com.characterBuilder.srvc.interfaces.EventMessageSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.properties.CharBuildProp;

@Service
public class EventMessageSrvcImpl implements EventMessageSrvc
{
	@Autowired
	EventMessageRepo emRepo;
	
	@Autowired
	CharBuildProp charProp;
	
	@Override
	@Transactional
	public void add(EventMessage eventMsg) throws ExceedingLimitException	
	{
		int msgLength = eventMsg.getMessage().length();
		int maxMsgLength = charProp.eventMessageLengthMax();
		if(msgLength <= maxMsgLength) {
			emRepo.save(eventMsg);
			this.removeExcess(eventMsg.getEventId());
		}
		else {
			throw new ExceedingLimitException(msgLength, maxMsgLength, "EventMessage");
		}
	}

	@Override
	public void delete(EventMessage eventMsg)
	{
		emRepo.delete(eventMsg);
	}

	@Override
	public List<EventMessage> getByUser(User user)
	{
		return emRepo.getEventMsgsByUserId(user.getId());		
	}
	
	private void removeExcess(long eventId) {
		int msgCount = (int)emRepo.countByEventId(eventId);
		int maxMsgCount = charProp.eventMessageCountMax();
		double overflowFactor = charProp.eventMessageOverflowFactor();
		if(msgCount > maxMsgCount * overflowFactor) {
			deleteOldMsgs(eventId);
		}
	}

	@Override
	@Transactional
	public void deleteOldMsgs(long eventId)
	{
		List<EventMessage> msgs = emRepo.findByEventId(eventId);
		Collections.sort(msgs, new ComparitorDateTime());
	
		int msgCount = msgs.size();
		int maxMsgCount = charProp.eventMessageCountMax();
		
		int end = msgCount;
		int start = maxMsgCount + 1;
		emRepo.delete(msgs.subList(start, end));
	}
}
