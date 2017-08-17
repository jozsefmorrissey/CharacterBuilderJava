package com.characterBuilder.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.repositories.EventRepo;
import com.characterBuilder.services.interfaces.DescriptionSrvc;
import com.characterBuilder.services.interfaces.EventSrvc;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegistered;

@Service
public class EventSrvcImpl implements EventSrvc {

    @Autowired
    private EventRepo eventRepo;

    @Override
    public Event getById(long id) {
        return eventRepo.getOne(id);
    }

    @Override
    @Transactional
    public void add(Event event) throws EmailAlreadyRegistered {
    	Event dne = eventRepo.getOne(event.getId());
    	if(dne != null)
    		throw new EmailAlreadyRegistered();

    	eventRepo.saveAndFlush(event);
    }

    @Override
    public void delete(Event event) {
        eventRepo.delete(event);
    }

    @Override
    public void update(Event event) {
        eventRepo.saveAndFlush(event);
    }

    @Override
    public Set<Event> getAll() {
    	List<Event> repo = eventRepo.findAll();
    	HashSet<Event> set = new HashSet<>(repo);
        return set;
    }

	@Override
	public Set<EventTime> getEventTimes(long id) {
		return null;//new HashSet(timeRepo.findAll());
	}

	@Override
	public Set<EventImage> getEventImages(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

