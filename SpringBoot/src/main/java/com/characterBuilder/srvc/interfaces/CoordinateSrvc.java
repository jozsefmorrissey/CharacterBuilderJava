package com.characterBuilder.srvc.interfaces;

import com.characterBuilder.entities.pureDBEntities.Coordinate;

public interface CoordinateSrvc {
	public Coordinate getById(long coordinateId);
	public void addCoordinate(Coordinate coordinate);
	public void deleteCoordinate(Coordinate coordinate);
}
