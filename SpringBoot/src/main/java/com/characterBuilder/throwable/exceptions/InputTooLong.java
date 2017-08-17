package com.characterBuilder.throwable.exceptions;

public class InputTooLong extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7562397188694890383L;
	
	private long recievedSize;
	private long expectedSize;
	
	public InputTooLong(long recievedSize, long expectedSize){
		this.recievedSize = recievedSize;
		this.expectedSize = expectedSize;
	}

	@Override
	public String getMessage() {
		return "Input recieved was of length (" + recievedSize + ") expected length of" +
				"less than (" + expectedSize + ")";
	}
}
