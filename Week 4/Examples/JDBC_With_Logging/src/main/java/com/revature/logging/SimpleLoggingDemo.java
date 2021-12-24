package com.revature.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoggingDemo {

	public static void main(String[] args) {
		
		/*
		 * I've chosen to use the Logger type from SL4J in order to lean into interface-driven
		 * development. This will lead to easier refactoring in the future if I should choose to
		 * refactor at any point.
		 */
		Logger myLogger = LoggerFactory.getLogger("aLogger");
		
		/*
		 * The level at which you log is highly dependent on your needs and preferences.
		 * The possible logging levels in Logback from least severe to most severe include:
		 * 
		 * TRACE
		 * DEBUG
		 * INFO
		 * WARN
		 * ERROR
		 * 
		 * If you choose to log at a particular level, you will log everything at or above that level.
		 * The logging level is currently set to "error" in logback.xml file, so "debug" statements
		 * will NOT be logged right now.
		 */
		myLogger.debug("debugging an issue at this point in my application");
		myLogger.error("logging at error");
	}
}
