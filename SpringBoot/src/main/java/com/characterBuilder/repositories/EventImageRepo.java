package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.EventImage;

public interface EventImageRepo extends JpaRepository<EventImage, Long> {
	public List<EventImage> getByEventId(long id);
	public void deleteByEventId(long id);
	public int countByEventId(long id);
}
