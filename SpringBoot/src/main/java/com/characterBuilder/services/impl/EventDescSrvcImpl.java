package com.characterBuilder.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.entities.pureDBEntities.EventDescription;
import com.characterBuilder.repositories.EventDescriptionRepo;
import com.characterBuilder.services.interfaces.DescriptionSrvc;
import com.characterBuilder.services.interfaces.EventDescriptionSrvc;
import com.characterBuilder.services.interfaces.EventSrvc;
import com.characterBuilder.throwable.exceptions.DependenciesNotFullfilledException;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;
import com.characterBuilder.util.PropertiesUtil;
import com.characterBuilder.util.StringUtil;

@Service
public class EventDescSrvcImpl implements EventDescriptionSrvc {

	@Autowired
	DescriptionSrvc descSrvc;
	
	@Autowired
	EventDescriptionRepo eventDescRepo;
	
	@Autowired
	EventSrvc eventSrvc;
	
	/**
	 * Adds a discription iff no description exists. To overwrite use update.
	 * @throws DependenciesNotFullfilledException 
	 */
	@Override
	public void add(Event event, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException {
		long id = event.getId();
		
		// Varify that no Description exists if a null value exists or the index is out
		// of bounds I force these two cases into the catch block which is the only place
		// additions to the database are made.
		try{
			String ed = descSrvc.getEventDescription(id);
			if(ed == null)
				throw new IndexOutOfBoundsException();
			
			throw new OverwritingDataException();
		}catch (IndexOutOfBoundsException e){
			this.divideAndAddData(event, description);
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
	@Transactional
	private void divideAndAddData(Event event, String description) throws InputTooLong {
		if(description.length() > PropertiesUtil.getDescriptionLength())
			throw new InputTooLong(description.length(), PropertiesUtil.getDescriptionLength());
		String[] strs = StringUtil.uniformLengthStrings(description, PropertiesUtil.getDesciptionSegmentLength());
		ArrayList<EventDescription> eventDescs = new ArrayList<EventDescription>();
		for(int index = 0; index < strs.length; index++){
			eventDescs.add(new EventDescription(0, event.getId(), strs[index], index));
		}
		eventDescRepo.save(eventDescs);
	}

	/**
	 * Deletes all event discriptions corresponding to event.id.
	 */
	@Override
	@Transactional
	public void delete(Event event) {
		eventDescRepo.deleteByEventId(event.getId());
	}

	/**
	 * Should delete descriptions and then adds. If it fails I would check deletes functionality first.
	 * @throws DependenciesNotFullfilledException 
	 */
	@Override
	@Transactional
	public void update(Event event, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException {
		delete(event);
		add(event, description);
	}

	@Override
	public List<Description> getAllDescriptions() {
		return descSrvc.getAllEventDesc();	
	}

	@Override
	public void setDescription(Event event) {
		String description = descSrvc.getEventDescription(event.getId());
		event.setDescription(description);
	}
}
