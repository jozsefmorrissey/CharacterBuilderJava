package com.characterBuilder.srvc.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.comparator.ComparitorDateTime;
import com.characterBuilder.entities.EventTimeMessage;
import com.characterBuilder.entities.User;
import com.characterBuilder.repo.EventTimeMessageRepo;
import com.characterBuilder.srvc.interfaces.EventTimeMessageSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.properties.CharBuildProp;

@Service
public class EventTimeMessageSrvcImpl implements EventTimeMessageSrvc
{

	@Autowired
	EventTimeMessageRepo etmRepo;
	
	@Autowired
	CharBuildProp charProp;

	@Override
	public List<EventTimeMessage> getByUser(User user)
	{
		return etmRepo.getEventTimeMsgsByUserId(user.getId());
	}

	@Override
	public void add(EventTimeMessage eventTimeMsg) throws ExceedingLimitException
	{
		int msgLength = eventTimeMsg.getMessage().length();
		int maxMsgLength = charProp.eventTimeMessageLengthMax();
		if(msgLength <= maxMsgLength) {
			etmRepo.save(eventTimeMsg);
			this.removeExcess(eventTimeMsg.getEventTimeId());
		}
		else {
			throw new ExceedingLimitException(msgLength, maxMsgLength, "EventTimeMessage");
		}
	}

	@Override
	public void delete(EventTimeMessage eventTimeMsg)
	{
		etmRepo.delete(eventTimeMsg);
	}

	@Override
	public void deleteOldMsgs(long eventTimeId)
	{
		List<EventTimeMessage> msgs = etmRepo.findByEventTimeId(eventTimeId);
		Collections.sort(msgs, new ComparitorDateTime());
	
		int msgCount = msgs.size();
		int maxMsgCount = charProp.eventTimeMessageCountMax();
		
		int end = msgCount;
		int start = maxMsgCount + 1;
		etmRepo.delete(msgs.subList(start, end));
	}

	private void removeExcess(long eventTimeId) {
		int msgCount = (int)etmRepo.countByEventTimeId(eventTimeId);
		int maxMsgCount = charProp.eventTimeMessageCountMax();
		double overflowFactor = charProp.eventTimeMessageOverflowFactor();
		if(msgCount > maxMsgCount * overflowFactor) {
			deleteOldMsgs(eventTimeId);
		}
	}
}
