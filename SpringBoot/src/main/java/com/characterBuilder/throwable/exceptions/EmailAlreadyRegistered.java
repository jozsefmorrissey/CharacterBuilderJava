package com.characterBuilder.throwable.exceptions;

public class EmailAlreadyRegistered extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205971822873022910L;
	private String msg = "An account is already registered to this email.";
	@Override
	public String getMessage() {
		return msg;
	}
	
}
