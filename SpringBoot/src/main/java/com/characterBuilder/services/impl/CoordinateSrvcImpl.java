package com.characterBuilder.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.pureDBEntities.Coordinate;
import com.characterBuilder.repositories.CoordinateRepo;
import com.characterBuilder.services.interfaces.CoordinateSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;

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
	public void addCoordinate(Coordinate coordinate) throws ExceedingLimitException {
		coordinateRepo.save(coordinate);
	}

	@Override
	@Transactional
	public void deleteCoordinate(Coordinate coordinate) {
		coordinateRepo.delete(coordinate);		
	}
}
