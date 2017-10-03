package com.characterBuilder.throwable.exceptions;

public class InvalidIdException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String fieldName;
	
	public InvalidIdException(String fieldName)
	{
		this.fieldName = fieldName;
	}

	@Override
	public String getMessage()
	{
		return "An invalid id was detected in field " + fieldName;
	}
}
