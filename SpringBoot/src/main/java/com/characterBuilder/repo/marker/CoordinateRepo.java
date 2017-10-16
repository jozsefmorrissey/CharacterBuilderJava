package com.characterBuilder.repo.marker;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.Coordinate;

public interface CoordinateRepo extends JpaRepository<Coordinate, Long> {

}
