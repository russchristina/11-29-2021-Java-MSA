package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreendaleLogger {
	
	public static void main(String[] args) {
		
		Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);
		
		myLogger.debug("debugging an issue at this point in my application");
		
	}
}
