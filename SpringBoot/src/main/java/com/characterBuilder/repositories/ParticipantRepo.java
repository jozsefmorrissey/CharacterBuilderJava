package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.Participant;

public interface ParticipantRepo extends JpaRepository<Participant, Long> {
	public List<Participant> getByEventTimeId(long id);
	public void deleteByEventTimeId(long id);
	public long countByEventTimeId(long id);
}
