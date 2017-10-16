package com.characterBuilder.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.Participant;

public interface ParticipantRepo extends JpaRepository<Participant, Long> {
	public List<Participant> findAllByIdEventTimeId(long id);
	public void deleteByIdEventTimeId(long id);
	public long countByIdEventTimeId(long id);
}
