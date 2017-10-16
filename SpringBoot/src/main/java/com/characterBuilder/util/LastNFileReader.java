package com.characterBuilder.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LastNFileReader
{	
	private Object[] lines;
	private String fileName;
	private int numberOfLines = 50;
	
	/**
	 * File name needs to be in Linux 
	 * format relative to project directory.
	 * @param fileName
	 */
	public LastNFileReader(String fileName) {
		this.fileName = fileName;
	}

	private void readLog() {
		Path file = Paths.get(fileName);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			lines = reader.lines().toArray();
			Collections.reverse(Arrays.asList(lines));
			lines = Arrays.copyOf(lines, numberOfLines); 
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	public List<String> findLines(String contains) {
		return getLines(contains, 0, Integer.MAX_VALUE);
	}

	private List<String> getLines(String contains, int start, int end)
	{
		readLog();
		List<String> hits = new ArrayList<String>();
		int index = 0;
		for(Object line: lines) {
			boolean overRange = index >= end;
			if(overRange)
				return hits;
			
			if(index >= start && line.toString().contains(contains)) {
				hits.add(line.toString());
			}
			index++;
		}
		return hits;
	}
	
	public List<String> findLines(String contains, int lineCount) {
		return getLines(contains, 0, lineCount);
	}
	
	public List<String> findLines(String contains, int start, int lineCount) {
		return getLines(contains, start, start + lineCount);
	}
	
	public int getNumberOfLines() {
		return numberOfLines;
	}
	
	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}
}
