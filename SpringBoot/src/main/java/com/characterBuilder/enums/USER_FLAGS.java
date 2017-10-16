package com.characterBuilder.enums;

public enum USER_FLAGS
{

	DISHONEST("Dishonest"),
	MULTIPLE_PROFILES("Multiple Profiles"),
	THIEF("Thief"),
	MURDERER("Murderer"),
	RAPIST("Rapist"),
	AVOID("Avoid");
	
	
	private String value;

	USER_FLAGS(String value) {
	        this.value = value;
	    }

	public String value()
	{
		return value;
	}

	@Override
	public String toString()
	{
		return value;
	}
}
