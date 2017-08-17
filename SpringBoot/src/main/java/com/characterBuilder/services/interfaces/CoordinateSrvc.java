package com.characterBuilder.services.interfaces;

import com.characterBuilder.entities.pureDBEntities.Coordinate;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;

public interface CoordinateSrvc {
	public Coordinate getById(long coordinateId);
	public void addCoordinate(Coordinate coordinate) throws ExceedingLimitException;
	public void deleteCoordinate(Coordinate coordinate);
}
