package com.characterBuilder.throwable.exceptions;

public class ExceedingLimitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8552686282359018925L;
	private long count;
	private long limit;
	private String objectType;
	
	public ExceedingLimitException (long count, long limit, String objectType) {
		this.count = count;
		this.limit = limit;
		this.objectType = objectType;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Operation attempted to add a total of " + count + " of " + objectType + " a limit of " + 
				limit + " exists.";
	}
}
