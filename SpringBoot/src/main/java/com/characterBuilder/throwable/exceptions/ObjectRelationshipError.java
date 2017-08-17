package com.characterBuilder.throwable.exceptions;

public class ObjectRelationshipError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 461896541360824625L;
	private String msg;
	
	public ObjectRelationshipError(Object one, Object two) {
		 msg = "An object of type " + one + " was found that was not linked"
		 		+ " to the corresponding object of type " + two + ".";
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
