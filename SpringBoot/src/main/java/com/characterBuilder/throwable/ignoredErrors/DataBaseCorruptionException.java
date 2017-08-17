package com.characterBuilder.throwable.ignoredErrors;

public class DataBaseCorruptionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5583618942402468164L;
	private String msg;
	
	public DataBaseCorruptionException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
