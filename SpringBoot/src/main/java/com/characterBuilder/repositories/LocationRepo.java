package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.Location;

public interface LocationRepo extends JpaRepository<Location, Long> {
	public List<Location> getByUserId(long id);
	public void deleteByUserId(long id);
	public long countByUserId(long id);
}
