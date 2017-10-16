package com.characterBuilder.srvc.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.Location;
import com.characterBuilder.entities.User;
import com.characterBuilder.repo.LocationRepo;
import com.characterBuilder.srvc.interfaces.CoordinateSrvc;
import com.characterBuilder.srvc.interfaces.LocationSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.properties.CharBuildProp;

@Service
public class LocationSrvcImpl implements LocationSrvc {

	@Autowired
	LocationRepo locRepo;
	
	@Autowired
	CoordinateSrvc coordSrvc;
	
	@Autowired
	CharBuildProp property;
	
	@Override
	public List<Location> getByUser(User user) {
		List<Location> locations = locRepo.getByUserId(user.getId());
		Collections.sort(locations);
		return locations;
	}

	@Override
	@Transactional
	public void addLocation(Location location) throws ExceedingLimitException {
		long max = property.coordinateCountMax();
		long count = locRepo.countByUserId(location.getUserId()) + 1; 
		if(count <= max) {
			coordSrvc.addCoordinate(location.getCoordinate());
			locRepo.save(location);
		}
		else
			throw new ExceedingLimitException(count, max, "Location");

	}

	@Override
	@Transactional
	public void removeLocation(Location location) {
		locRepo.delete(location);
	}

	@Override
	@Transactional
	public void removeAllByUser(User user) {
		locRepo.deleteByUserId(user.getId());
	}

}
