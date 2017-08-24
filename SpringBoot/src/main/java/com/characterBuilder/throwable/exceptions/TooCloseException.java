package com.characterBuilder.throwable.exceptions;

public class TooCloseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -794177419782645106L;

	private String type;
	private String unit;
	private int distance;

	public TooCloseException(String type, String unit, int distance) {
		this.type = type;
		this.unit = unit;
		this.distance = distance;
	}

	@Override
	public String getMessage() {
		
		
		return type + " can only be added atleast " + distance + " " + unit + " apart.";
	}
	
}
