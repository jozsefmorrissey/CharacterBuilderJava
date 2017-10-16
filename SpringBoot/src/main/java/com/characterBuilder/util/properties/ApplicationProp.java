package com.characterBuilder.util.properties;

import org.springframework.stereotype.Component;

import com.characterBuilder.util.PropertiesAbs;

@Component
public class ApplicationProp extends PropertiesAbs
{
	private final static String fileName = "\\src\\main\\resources\\application.properties";

	public ApplicationProp()
	{
		super(fileName);
	}

	/*
	 * The following functions just retrieve the indicated property.
	 */
	public String loggingPatternFile()
	{
		return getString("logging.pattern.file");
	}

}
