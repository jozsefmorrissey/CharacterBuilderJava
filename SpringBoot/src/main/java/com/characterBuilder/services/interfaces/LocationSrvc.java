package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.Location;
import com.characterBuilder.entities.User;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;

public interface LocationSrvc {
	public List<Location> getByUser(User user);
	
	public void addLocation(Location location) 
			throws ExceedingLimitException;
	
	public void removeAllByUser(User user);
	public void removeLocation(Location location);
}
