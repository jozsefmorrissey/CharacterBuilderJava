package com.characterBuilder.throwable.exceptions;

public class OverwritingDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4646213201739923340L;
	
	private String msg = "Attempted to overwrite data (use update methods if this is your intention.";
	@Override
	public String getMessage() {
		return msg;
	}
	
}
