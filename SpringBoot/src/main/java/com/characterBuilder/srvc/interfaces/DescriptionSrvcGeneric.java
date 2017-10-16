package com.characterBuilder.srvc.interfaces;

import java.util.List;

import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.throwable.exceptions.DependenciesNotFullfilledException;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;

public interface DescriptionSrvcGeneric<T, U> {

	void add(U obj, String description)
			throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException;

	void delete(U obj);

	void update(U obj, String description)
			throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException;

	List<Description> getAllDescriptions();

	void setDescription(U obj);

}
