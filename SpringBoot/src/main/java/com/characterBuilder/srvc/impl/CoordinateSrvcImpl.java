package com.characterBuilder.srvc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.pureDBEntities.Coordinate;
import com.characterBuilder.repo.marker.CoordinateRepo;
import com.characterBuilder.srvc.interfaces.CoordinateSrvc;

@Service
public class CoordinateSrvcImpl implements CoordinateSrvc {

	@Autowired
	CoordinateRepo coordinateRepo;
	
	@Override
	@Transactional
	public Coordinate getById(long id) {
		return coordinateRepo.getOne(id);
	}

	@Override
	@Transactional
	public void addCoordinate(Coordinate coordinate) {
		coordinateRepo.save(coordinate);
	}

	@Override
	@Transactional
	public void deleteCoordinate(Coordinate coordinate) {
		coordinateRepo.delete(coordinate);		
	}
}
