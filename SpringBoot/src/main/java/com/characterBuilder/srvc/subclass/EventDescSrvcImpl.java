package com.characterBuilder.srvc.subclass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.entities.pureDBEntities.EventDescription;
import com.characterBuilder.markers.DescriptionRepoMarker;
import com.characterBuilder.repo.EventDescriptionRepo;
import com.characterBuilder.srvc.impl.DescriptionSrvcAbs;
import com.characterBuilder.srvc.interfaces.EventDescriptionSrvc;

@Service
public class EventDescSrvcImpl 
		extends DescriptionSrvcAbs<EventDescription, Event> 
		implements EventDescriptionSrvc {

	@Autowired
	EventDescriptionRepo eventDescRepo;
	
	protected DescriptionRepoMarker<EventDescription> getDescRepo() {
		return this.eventDescRepo;
	}
	
	@Override
	public List<Description> getAllDescriptions()
	{
		return getDescSrvc().getAllEventDesc();
	}
	
	@Override
	protected String getDescription(long id)
	{
		return getDescSrvc().getEventDescription(id);
	}
	
	@Override
	protected EventDescription create(long id, String description, int position) {
		return new EventDescription(id, description, position);
	}
}
