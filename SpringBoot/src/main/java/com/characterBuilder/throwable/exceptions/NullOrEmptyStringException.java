package com.characterBuilder.throwable.exceptions;

public class NullOrEmptyStringException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 811103747214873638L;
	
	String fieldName;
	
	public NullOrEmptyStringException(String fieldName)
	{
		this.fieldName = fieldName;
	}

	@Override
	public String getMessage()
	{
		return "An invalid id was detected in field " + fieldName;
	}
}
