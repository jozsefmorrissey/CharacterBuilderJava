package com.characterBuilder.srvc.interfaces;

import java.util.Collection;
import java.util.List;

import com.characterBuilder.entities.Participant;
import com.characterBuilder.throwable.exceptions.AddingConflictingIds;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.throwable.exceptions.ZeroIdException;

public interface ParticipantSrvc {
	public List<Participant> getParticipants(long eventTimeId);
	
	public void addParticipant(Participant participant) 
			throws ExceedingLimitException, ZeroIdException;
	public void addAllParticipants(Collection<Participant> participant) 
			throws AddingConflictingIds, ZeroIdException, ExceedingLimitException;
	
	public void removeParticipant(Participant participant);
	public void removeAllParticipants(Collection<Participant> participant);
	public void removeAllParticipants(long eventTimeId);
}
