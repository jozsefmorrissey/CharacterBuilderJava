package com.characterBuilder.appender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LastN extends AppenderSkeleton
{
	public LastN() {
		super();
        this.setLayout(new PatternLayout("%d [%X{requestURIWithQueryString}] %-5p -[%t] %m  [%c{1}:%M %L] %n"));
        setName("UI_APPENDER");
        setThreshold(org.apache.log4j.Level.DEBUG);
	}
}
