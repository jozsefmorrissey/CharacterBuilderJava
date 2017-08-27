package com.characterBuilder.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.characterBuilder.entities.Event;
import com.characterBuilder.services.interfaces.EventSrvc;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegisteredException;

@RestController
@RequestMapping("event")
public class EventCtrl {
	@Autowired
	private EventSrvc eventSrvc;

	public EventCtrl(EventSrvc eventSrvc) {
	  super();
	  this.eventSrvc = eventSrvc;
	}

	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Event event, HttpSession session) throws EmailAlreadyRegisteredException {
	  eventSrvc.add(event);
	  return ResponseEntity.ok(null);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteEvent(@RequestBody Event event, HttpSession session) {
	  eventSrvc.delete(event);
	  return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<Object> updateEvent(@RequestBody Event event, HttpSession session) {
	    //TODO: AOP login varification.
	    eventSrvc.update(event);
	    return ResponseEntity.ok(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<Event>> getEvent(@PathVariable long id, HttpSession session) {
	  List<Event> event = new ArrayList<Event>();
	  event.add(eventSrvc.getById(id));
	  return ResponseEntity.ok(event);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Event>> getAllEvents(HttpSession session) {
	  return ResponseEntity.ok(eventSrvc.getAll());
	}
}
