package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.Event;

public interface EventRepo extends JpaRepository<Event, Long>{

}
