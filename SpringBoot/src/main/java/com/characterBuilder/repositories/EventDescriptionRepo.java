package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.EventDescription;

public interface EventDescriptionRepo extends JpaRepository<EventDescription, Long>{
	public void deleteByDescIdId(long id);
}
