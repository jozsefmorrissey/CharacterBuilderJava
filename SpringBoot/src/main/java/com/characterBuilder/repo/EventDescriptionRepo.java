package com.characterBuilder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.EventDescription;
import com.characterBuilder.markers.DescriptionRepoMarker;

public interface EventDescriptionRepo
		extends JpaRepository<EventDescription, Long>,
			DescriptionRepoMarker<EventDescription>{
	public void deleteByDescIdId(long id);
}
