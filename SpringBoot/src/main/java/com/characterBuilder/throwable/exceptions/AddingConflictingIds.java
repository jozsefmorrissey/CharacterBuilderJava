package com.characterBuilder.throwable.exceptions;

public class AddingConflictingIds extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6068069081446689703L;

	private Object obj1;
	private Object obj2;
	private String msg;
	
	public AddingConflictingIds(Object obj1, Object obj2, String msg) {
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return "Do to the requirements of the programming logic the following two "
				+ "objects posses conflicting ids:\n" + obj1 + "\n" + obj2 + "\n" + msg;
	}
}
