package com.characterBuilder.throwable.exceptions;

import com.characterBuilder.entities.Event;

public class DependenciesNotFullfilledException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5688348836704824431L;
	private String msg;

	public DependenciesNotFullfilledException(Class<Event> class1, long id) {
		msg = "Missing dependencies of class " + class1.toString() + " with id " + id + ".";
	}

	@Override
	public String getMessage() {
		return msg;
	}

}
