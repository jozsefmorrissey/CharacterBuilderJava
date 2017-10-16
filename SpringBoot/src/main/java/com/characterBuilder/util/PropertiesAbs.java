package com.characterBuilder.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertiesAbs
{
	private Properties prop;

	/**
	 * Private constructor allows the creation of a singleton, once the
	 * properties have been read once it will be accessible to all.
	 */
	protected PropertiesAbs(String fileName)
	{
		try
		{
			prop = new Properties();

			String basePath = System.getProperty("user.dir");

			InputStream inputStream = new FileInputStream(basePath + fileName);

			if (inputStream != null)
			{
				prop.load(inputStream);
			}
		} catch (IOException e)
		{
			System.out.println("Error Loading charBuild.properties this file should "
					+ "be located in Springbood/src/resource/: " + e);
		}
	}

	/**
	 * The following functions retreive and convert the given property
	 */
	protected int getInteger(String propertyName)
	{
		return Integer.parseInt(prop.getProperty(propertyName));
	}

	protected double getDouble(String propertyName)
	{
		return Double.parseDouble(prop.getProperty(propertyName));
	}

	protected String getString(String propertyName)
	{
		return prop.getProperty(propertyName);
	}
}
