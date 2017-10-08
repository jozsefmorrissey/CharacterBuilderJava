package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.throwable.exceptions.DependenciesNotFullfilledException;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;

public interface UserDescriptionSrvc
{
	public void add(User user, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException;
	public void delete(User user);
	public void update(User user, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException;
	public List<Description> getAllDescriptions();
	public void setDescription(User user);
}
