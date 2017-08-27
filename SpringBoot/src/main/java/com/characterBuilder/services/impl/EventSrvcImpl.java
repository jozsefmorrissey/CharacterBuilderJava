package com.characterBuilder.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.User;
import com.characterBuilder.repositories.EventRepo;
import com.characterBuilder.services.interfaces.EventSrvc;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegisteredException;

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
    public void add(Event event) throws EmailAlreadyRegisteredException {
    	Event dne = eventRepo.getOne(event.getId());
    	if(dne != null)
    		throw new EmailAlreadyRegisteredException();

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
    @Transactional
    public List<Event> getAll() {
    	List<Event> repo = eventRepo.findAll();
        return repo;
    }
    
    @Override
    @Transactional
    public List<Event> getAllByPoster(User poster) {
    	return eventRepo.findByPoster(poster);
    }
}

