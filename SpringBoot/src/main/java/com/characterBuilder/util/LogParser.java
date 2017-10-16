package com.characterBuilder.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.characterBuilder.util.properties.ApplicationProp;

@Component
public class LogParser
{
	private enum PatternDiscriminator {
		THREAD("thread"),
		DATE_TIME("%d{"),
		LEVEL("level"),
		LOGGER("logger"),
		MESSAGE("msg"),
		SPLITTER("~");
		private int index = -1;
		private final String indicator;
		
		private PatternDiscriminator(String indicator) {
			this.indicator = indicator;
		}
		
		private void setIndex(int index) {
			this.index = index;
		}
		
		@Override
		public String toString() {
			return indicator;
		}
	}
	
	private String pattern;
	private String datePattern;
	private PatternDiscriminator[] indicators = PatternDiscriminator.values();
	
	@Autowired
	public LogParser(ApplicationProp appProp) {
		pattern = appProp.loggingPatternFile();
		interpertPatturn();
	}
	
	public LogParser(String pattern) {
		this.pattern = pattern;
		interpertPatturn();
	}
	
	private void interpertPatturn() {
		String[] pieces = breakDown(pattern);
		for(PatternDiscriminator pd : indicators) {
			int index = 0;
			for(String str : pieces) {
				if(str.contains(pd.indicator)) {
					pd.index = index;
				}
				index++;
			}
		}
		String dateP = getPiece(PatternDiscriminator.DATE_TIME, pattern);
		datePattern = dateP.substring(dateP.indexOf("d{") + 2, dateP.indexOf("}"));
	}
	
	private String[] breakDown(String line) {
		String[] info0Msg1 = line.split(getSplitter());
		String[] infoBits = info0Msg1[0].split(" ");
		String[] allParts = Arrays.copyOf(infoBits, infoBits.length + 1);
		allParts[infoBits.length] = info0Msg1[1];
		return allParts;
	}
	
	private String getPiece(PatternDiscriminator pd, String line) {
		return breakDown(line)[pd.index].trim();
	}
	
	public String getThread(String line) {
		String rawStr = getPiece(PatternDiscriminator.THREAD, line);
		return rawStr.substring(1, rawStr.length() - 1);
	}
	
	public LocalDateTime getDateTime(String line) {
		String dateStr = getPiece(PatternDiscriminator.DATE_TIME, line);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		return LocalDateTime.parse(dateStr, formatter);
	}

	public String getLevel(String line) {
		return getPiece(PatternDiscriminator.LEVEL, line);
	}
	
	public String getLogger(String line) {
		return getPiece(PatternDiscriminator.LOGGER, line);
	}
	
	public String getMessage(String line) {
		return getPiece(PatternDiscriminator.MESSAGE, line);
	}
	
	public String getSplitter() {
		return PatternDiscriminator.SPLITTER.toString();
	}
}
