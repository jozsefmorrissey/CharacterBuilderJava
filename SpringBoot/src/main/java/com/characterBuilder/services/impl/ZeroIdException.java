package com.characterBuilder.services.impl;

public class ZeroIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2111862905369557276L;
	
	private Object obj;
	
	public ZeroIdException(Object obj) {
		this.obj = obj;
	}
	
	public String getMessage() {
		return "An attempt was made to persist an object that contained reference "
				+ "to a non persisted object with an Id of zero: " + obj;
		
	}
}
