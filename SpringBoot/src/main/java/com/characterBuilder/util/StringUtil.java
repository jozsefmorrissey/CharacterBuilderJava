package com.characterBuilder.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StringUtil {

	public static String[] uniformLengthStrings(String str, int length){
		ArrayList<String> strs = new ArrayList<String>();
		int index = 0;
		while(str.length() > index){
			int endIndex = index + length;
			endIndex = (endIndex > str.length() ? str.length() : endIndex);
			String subStr = str.substring(index, endIndex);
			String section = subStr;
			strs.add(section);
			index = endIndex;
		}
		String[] strArr = new String[0];
		return strs.toArray(strArr);
	}
	
	public static LocalDateTime localDateTimeConverter(String tsString) {
		tsString = tsString.replace('T', ' ');
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"); 
		return LocalDateTime.parse(tsString, formatter);
	}
	
	public static String[] commaSepToArr(String commaSep) {
		return commaSep.replaceAll("\\s", "").split(",");
	}
}
