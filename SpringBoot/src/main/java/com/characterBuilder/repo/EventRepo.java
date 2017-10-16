package com.characterBuilder.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.User;

public interface EventRepo extends JpaRepository<Event, Long>{

	public List<Event> findByPoster(User poster);
}
