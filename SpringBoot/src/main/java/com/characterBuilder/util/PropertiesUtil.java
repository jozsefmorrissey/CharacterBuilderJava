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
	
	/**
	 * Verifies propUtil has been set.
	 */
	private static void initialize(){
		if(propUtil == null)
			propUtil = new PropertiesUtil();
	}
	
	/**
	 * The following functions retreive and convert the given property
	 */
	private static int getInteger(String propertyName){
		initialize();
		return Integer.parseInt(propUtil.prop.getProperty(propertyName));
	}
	
	private static double getDouble(String propertyName){
		initialize();
		return Double.parseDouble(propUtil.prop.getProperty(propertyName));
	}
	
	/*
	 * The following functions just retrieve the indicated property.
	 */
	public static int descriptionSegmentLength(){
		return getInteger("desciption.segment.length");
	}
	
	public static int descriptionLength(){
		return getInteger("description.length");
	}	
	
	public static int imageCountMax() {
		return getInteger("image.count.max");
	}
	
	public static int eventTimeCountMax() {
		return getInteger("eventTime.count.max");
	}
	
	public static int coordinateCountMax() {
		return getInteger("coordinate.count.max");
	}
	
	public static int participantMax() {
		return getInteger("participant.max");	
	}
	
	public static int daysBetweenUserRatingsMin() {
		return getInteger("daysBetweenUserRatings.min");	
	}
	
	public static int messageLengthMax() {
		return getInteger("message.length.max");
	}
	
	public static int messageCountMax() {
		return getInteger("message.count.max");
	}
	
	public static int messageCountConversationMax() {
		return getInteger("message.count.conversation.max");
	}
	
	public static double messageOverflowFactor() {
		return getDouble("message.overflowFactor");
	}
	public static int eventMessageLengthMax() {
		return getInteger("eventMessage.length.max");
	}
	
	public static int eventMessageCountMax() {
		return getInteger("eventMessage.count.max");
	}
	
	public static double eventMessageOverflowFactor() {
		return getDouble("eventMessage.overflowFactor");
	}
	
	public static int eventTimeMessageLengthMax() {
		return getInteger("eventTimeMessage.length.max");
	}
	
	public static int eventTimeMessageCountMax() {
		return getInteger("eventTimeMessage.count.max");
	}
	
	public static double eventTimeMessageOverflowFactor() {
		return getDouble("eventTimeMessage.overflowFactor");
	}
}
