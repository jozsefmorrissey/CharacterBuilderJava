package com.characterBuilder.abs;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
	private
	DescriptionSrvc descSrvc;
	
	protected abstract String getDescription(long id);
	protected abstract DescriptionRepoMarker<T> getDescRepo();
	protected abstract T create(long id, String description, int position);
	
	/**
	 * Override to control the size of the strings the
	 * description is broken down into.
	 * 
	 * Note: this should only be modified if the database
	 * 		 schema is changed.
	 * @return
	 */
	protected int getDescriptionSegmentLength() {
		return PropertiesUtil.descriptionSegmentLength();
	}
	
	/**
	 * Override to control the max length
	 * 
	 * @return - max allowable length
	 */
	protected int getDescriptionLength() {
		return PropertiesUtil.descriptionLength();
	}

	
	/**
	 * Adds a description iff no description exists. To overwrite use update.
	 * @throws DependenciesNotFullfilledException 
	 */
	@Override
	public void add(U obj, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException {
		long id = obj.getId();
		
		// Verify that no Description exists if a null value exists or the index is out
		// of bounds these two cases are forced into the catch block which is the only place
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
	 * @param description - the description of the event
	 * @throws InputTooLong - Exception thrown if description exceeds descriptionLength that is set
	 * 						  in charBuild.properties.
	 */
	@Transactional
	private void divideAndAddData(U obj, String description) throws InputTooLong {
		if(description.length() > getDescriptionLength())
			throw new InputTooLong(description.length(), getDescriptionLength());
		String[] strs = StringUtil.uniformLengthStrings(description, getDescriptionSegmentLength());
		ArrayList<T> descs = new ArrayList<T>();
		for(int index = 0; index < strs.length; index++){
			T newT = create(obj.getId(), strs[index], index);
			descs.add(newT);
			getDescRepo().save(newT);
		}
		//getDescRepo().save(descs);
	}

	/**
	 * Deletes all event descriptions corresponding to event.id.
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
	public DescriptionSrvc getDescSrvc()
	{
		return descSrvc;
	}
	public void setDescSrvc(DescriptionSrvc descSrvc)
	{
		this.descSrvc = descSrvc;
	}
}
