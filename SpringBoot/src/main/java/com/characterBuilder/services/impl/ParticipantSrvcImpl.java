package com.characterBuilder.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.Participant;
import com.characterBuilder.repositories.ParticipantRepo;
import com.characterBuilder.services.interfaces.ParticipantSrvc;
import com.characterBuilder.throwable.exceptions.AddingConflictingIds;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.throwable.exceptions.ZeroIdException;
import com.characterBuilder.util.PropertiesUtil;

@Service
public class ParticipantSrvcImpl implements ParticipantSrvc {

	@Autowired
	ParticipantRepo partRepo;
	
	@Override
	public List<Participant> getParticipants(long eventTimeId) {
		List<Participant> parts = partRepo.getByEventTimeId(eventTimeId);
		Collections.sort(parts);
		return parts;
	}

	@Override
	@Transactional
	public void addParticipant(Participant participant) 
			throws ExceedingLimitException, ZeroIdException {
		verifyRelations(participant);
		verifyLimit(1, participant.getEventTimeId());
		partRepo.save(participant);
	}

	@Override
	public void addAllParticipants(Collection<Participant> participants) throws AddingConflictingIds, ZeroIdException, ExceedingLimitException {
		verifyEventTimes(participants);
		long etId = participants.iterator().next().getEventTimeId();
		verifyLimit(participants.size(), etId);
		partRepo.save(participants);
	}

	@Override
	@Transactional
	public void removeParticipant(Participant participant) {
		partRepo.delete(participant);
	}

	@Override
	public void removeAllParticipants(Collection<Participant> participant) {
		partRepo.delete(participant);
	}

	@Override
	public void removeAllParticipants(long eventTimeId) {
		partRepo.deleteByEventTimeId(eventTimeId);
	}
	
	private void verifyEventTimes(Collection<Participant> participants) 
			throws AddingConflictingIds, ZeroIdException {
		long etId = 0L;
		Participant initial = null;
		for(Participant p : participants) {
			verifyRelations(p);
			
			if(0 == etId) {
				etId = p.getEventTimeId();
				initial = p;
			}
			if(etId != p.getEventTimeId())
				throw new AddingConflictingIds(initial, p, "Participents can only be added together "
						+ "if they correspond to the same EventTime(Event and Time)");
		}
	}
	
	private void verifyLimit(int addedCount, long eventTimeId) 
			throws ExceedingLimitException {
		long countDb = partRepo.countByEventTimeId(eventTimeId);
		long maxCount = PropertiesUtil.participantMax();
		if(countDb + addedCount > maxCount)
			throw new ExceedingLimitException(addedCount + countDb, maxCount, "Participant related to a specific EventTime");
	}
	
	private void verifyRelations(Participant participant) 
			throws ZeroIdException {
		if(participant.getEventTimeId() == 0 || 
				participant.getParticipant().getId() == 0)
			throw new ZeroIdException(participant);
	}

}
