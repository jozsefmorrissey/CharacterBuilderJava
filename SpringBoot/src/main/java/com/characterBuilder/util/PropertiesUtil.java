package com.characterBuilder.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static PropertiesUtil propUtil= null;
	private Properties prop = null;

	/**
	 * Private constructor allows the creation of a singleton, once the properties
	 * have been read once it will be accessible to all.
	 */
	private PropertiesUtil(){
		try {
			prop = new Properties();
 
			String basePath = System.getProperty("user.dir");

			InputStream inputStream = new FileInputStream(basePath + "\\src\\main\\resources\\charBuild.properties");
			
			if (inputStream != null) {
				prop.load(inputStream);
			} 					 
		} catch (IOException e) {
			System.out.println("Error Loading charBuild.properties this file should " +
								"be located in Springbood/src/resource/: " + e);
		} 
	}
	
	private static void initialize(){
		if(propUtil == null)
			propUtil = new PropertiesUtil();
	}
	
	/*
	 * The following functions just retrieve the indicated property.
	 */
	
	public static int getDesciptionSegmentLength(){
		initialize();
		return Integer.parseInt(propUtil.prop.getProperty("DesciptionSegmentLength"));
	}
	
	public static int getDescriptionLength(){
		initialize();
		return Integer.parseInt(propUtil.prop.getProperty("DescriptionLength"));
	}	
	
	public static int getMaxImageCount() {
		initialize();
		return Integer.parseInt(propUtil.prop.getProperty("MaxImageCount"));
	}
	
	public static int getMaxEventTimeCount() {
		initialize();
		return Integer.parseInt(propUtil.prop.getProperty("MaxEventTimeCount"));
	}
	
	public static int getMaxCoordinateCount() {
		initialize();
		return Integer.parseInt(propUtil.prop.getProperty("MaxCoordinateCount"));
	}
	
	public static int getMaxParticipants() {
		initialize();
		return Integer.parseInt(propUtil.prop.getProperty("MaxParticipants"));	
	}
	
	public static int getDaysBetweenUserRatings() {
		initialize();
		return Integer.parseInt(propUtil.prop.getProperty("DaysBetweenUserRatings"));	
	}
}
