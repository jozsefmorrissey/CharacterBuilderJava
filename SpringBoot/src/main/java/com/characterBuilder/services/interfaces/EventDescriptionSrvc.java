package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.throwable.exceptions.DependenciesNotFullfilledException;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;

public interface EventDescriptionSrvc {
	public void add(Event event, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException;
	public void delete(Event event);
	public void update(Event event, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException;
	public List<Description> getAllDescriptions();
	public void setDescription(Event event);
}
