package com.characterBuilder.util;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LastNFileReaderTest
{
	Logger log = Logger.getRootLogger();
	String msg = "867-5309";
	@Test
	public void testContains() {
		log.error(msg);
		LastNFileReader fr= new LastNFileReader("logs/myapplication.log");
		System.out.println(fr.findLines(msg).size());
		System.out.println(fr.findLines("EndpointHandlerMapping").size());
	}
}
