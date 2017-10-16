package com.characterBuilder.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.characterBuilder.entities.EventMessage;

public interface EventMessageRepo extends JpaRepository<EventMessage, Long>
{
	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(FIND_EVENT_MESSAGES(:id))")
	public List<EventMessage> getEventMsgsByUserId(@Param(value = "id") long id);
	
	public long countByEventId(long id);
	public List<EventMessage> findByEventId(long id);
}
