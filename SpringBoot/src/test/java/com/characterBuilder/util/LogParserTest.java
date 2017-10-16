package com.characterBuilder.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.util.properties.ApplicationProp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogParserTest
{
	private static final String HALF_MESSAGE = "Even Number";
	private static final String SIXTH_MESSAGE = "One Sixth";
	private static final String ALL_MESSAGE = "TEST LINE";

	@Autowired
	ApplicationProp appProp;
	
	@Autowired
	LogParser logParser;
	
	String testFilePathWindows = "\\src\\test\\resources\\lastNFileReaderTest.txt";
	String relativeFilePathLinux =  "src/test/resources/lastNFileReaderTest.txt";
	Logger log = Logger.getLogger("new logger");
	String msg = "867-5309";
	
	
	@Test
	public void testContains() {
//		log.error(msg);
//		LastNFileReader fr= new LastNFileReader("logs/myapplication.log");
//		System.out.println("pat-urn: " + appProp.loggingPatternFile());
//		System.out.println(fr.findLines(msg));
//		System.out.println(fr.findLines("EndpointHandlerMapping").size());
//		
//		String line = fr.findLines(msg).get(0);
//		System.out.println("THREAD: '" + logParser.getThread(line) + "'");
//		System.out.println("DATE_TIME: '" + logParser.getDateTime(line) + "'");
//		System.out.println("LEVEL: '" + logParser.getLevel(line) + "'");
//		System.out.println("LOGGER: '" + logParser.getLogger(line) + "'");
//		System.out.println("MESSAGE: '" + logParser.getMessage(line) + "'");

		writeTestFile();
		LastNFileReader fr= new LastNFileReader(relativeFilePathLinux);
		setAndCheckNumberLines(fr, 1000);
		setAndCheckNumberLines(fr, 50);
		setAndCheckNumberLines(fr, 9);
		setAndCheckNumberLines(fr, 8);
	}
	
	@Test
	public void testContatinsRange() {
		writeTestFile();
		LastNFileReader fr= new LastNFileReader(relativeFilePathLinux);
		setAndCheckRange(fr, 0, 50, 50);
		setAndCheckRange(fr, 0, 25, 50);
		setAndCheckRange(fr, 500, 588, 100);
		setAndCheckRange(fr, 234, 486, 1000);
	}
	
	private void setAndCheckRange(LastNFileReader fr, int start, int end, int lineCount) {
		fr.setNumberOfLines(lineCount);
		int retLineCount = end - start;
		
		int expectedCount;
		if(start > lineCount) {
			expectedCount = 0;
		}
		else {
			expectedCount = retLineCount < lineCount ? retLineCount : lineCount;			
		}
		
		int allmsgCount = fr.findLines(ALL_MESSAGE, start, retLineCount).size();
		assert(allmsgCount == expectedCount);
	}
	
	private void setAndCheckNumberLines(LastNFileReader fr, int count) {
		fr.setNumberOfLines(count);

		int allmsgCount = fr.findLines(ALL_MESSAGE).size();
		assert(allmsgCount == count);
		
		int halfMsgCount = fr.findLines(HALF_MESSAGE).size();
		assert(halfMsgCount == (count/2));

		int sixethMsgCount = fr.findLines(SIXTH_MESSAGE).size();
		assert(sixethMsgCount == ((3 + count)/6));
	}
	
	private void writeTestFile()
	{
		String testFileContents = "";
		for(int i = 0; i < 1000; i++) {
			String extraMsg = "";
			if((1000 - i) % 2 == 0)
				extraMsg = HALF_MESSAGE;
			if((1000 - i) % 3 == 0 && (1000 - i) % 2 != 0)
				extraMsg = SIXTH_MESSAGE;
			testFileContents += (1000 - i) + ") " + ALL_MESSAGE + " - " + extraMsg + "\n";
		}
		try
		{
			String basePath = System.getProperty("user.dir");
			FileUtils.writeStringToFile(new File(basePath + testFilePathWindows), testFileContents.substring(0, testFileContents.length() - 1));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
