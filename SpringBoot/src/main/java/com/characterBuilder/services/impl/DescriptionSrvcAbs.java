package com.characterBuilder.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.markers.DescriptionAbs;
import com.characterBuilder.markers.DescriptionRepoMarker;
import com.characterBuilder.markers.HasIdDesc;
import com.characterBuilder.services.interfaces.DescriptionSrvc;
import com.characterBuilder.services.interfaces.DescriptionSrvcGeneric;
import com.characterBuilder.throwable.exceptions.DependenciesNotFullfilledException;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;
import com.characterBuilder.util.PropertiesUtil;
import com.characterBuilder.util.StringUtil;

public abstract class DescriptionSrvcAbs<T extends DescriptionAbs, U extends HasIdDesc>
		implements DescriptionSrvcGeneric<T, U>
{

	@Autowired
	DescriptionSrvc descSrvc;
	
	protected abstract String getDescription(long id);
	protected abstract DescriptionRepoMarker<T> getDescRepo();
	protected abstract T create(long id, String description, int position);

	
	/**
	 * Adds a discription iff no description exists. To overwrite use update.
	 * @throws DependenciesNotFullfilledException 
	 */
	@Override
	public void add(U obj, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException {
		long id = obj.getId();
		
		// Varify that no Description exists if a null value exists or the index is out
		// of bounds I force these two cases into the catch block which is the only place
		// additions to the database are made.
		try{
			String ed = getDescription(id);
			if(ed == null)
				throw new IndexOutOfBoundsException();
			
			throw new OverwritingDataException();
		}catch (IndexOutOfBoundsException e){
			this.divideAndAddData(obj, description);
		}
	}

	/**
	 * 	// This function divides the description string into 248 char strings. The size in the database
	// Is 256 but testing yielded that 248 was the largest string they would accept. The position
	// attribute is used to properly reassemble these strings when retrieved.
	 * 
	 * @param event - event connected to description
	 * @param description - the discription of the event
	 * @throws InputTooLong - Exception thrown if description exceeds descriptionLength that is set
	 * 						  in charBuild.properties.
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	private void divideAndAddData(U obj, String description) throws InputTooLong {
		if(description.length() > PropertiesUtil.descriptionLength())
			throw new InputTooLong(description.length(), PropertiesUtil.descriptionLength());
		String[] strs = StringUtil.uniformLengthStrings(description, PropertiesUtil.descriptionSegmentLength());
		ArrayList<T> descs = new ArrayList<T>();
		for(int index = 0; index < strs.length; index++){
			T newT = create(obj.getId(), strs[index], index);
			descs.add(newT);
			getDescRepo().save(newT);
		}
		//getDescRepo().save(descs);
	}

	/**
	 * Deletes all event discriptions corresponding to event.id.
	 */
	@Override
	@Transactional
	public void delete(U obj) {
		this.getDescRepo().deleteByDescIdId(obj.getId());
	}

	/**
	 * Should delete descriptions and then adds. If it fails I would check deletes functionality first.
	 * @throws DependenciesNotFullfilledException 
	 */
	@Override
	@Transactional
	public void update(U obj, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException {
		delete(obj);
		add(obj, description);
	}

	@Override
	public void setDescription(U obj) {
		String description = getDescription(obj.getId());
		obj.setDescription(description);
	}
}
