package com.characterBuilder.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.characterBuilder.entities.EventTimeMessage;

public interface EventTimeMessageRepo extends JpaRepository<EventTimeMessage, Long>
{
	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(FIND_EVENT_TIME_MESSAGES(:id))")
	public List<EventTimeMessage> getEventTimeMsgsByUserId(@Param(value = "id") long id);
	
	public long countByEventTimeId(long id);
	public List<EventTimeMessage> findByEventTimeId(long id);
}
