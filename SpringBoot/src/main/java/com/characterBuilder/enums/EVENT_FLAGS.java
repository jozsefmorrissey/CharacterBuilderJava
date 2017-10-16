package com.characterBuilder.enums;

public enum EVENT_FLAGS
{

	DANGER("Danger"),
	MISLEADING("Misleading"),
	UNPRODUCTIVE("Unproductive"),
	AVOID("Avoid");
	
	
	private String value;

	EVENT_FLAGS(String value) {
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
