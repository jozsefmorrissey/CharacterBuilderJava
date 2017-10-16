package com.characterBuilder.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.EventTime;

public interface EventTimeRepo extends JpaRepository<EventTime, Long> {
	public List<EventTime> getByEventId(long id);
	public void deleteByEventId(long id);
}
