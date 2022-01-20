package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreendaleLogger {
	
	private static final Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);
	
	public static void main(String[] args) {
	
		myLogger.debug("debug log from {}", GreendaleLogger.class.getSimpleName());
		myLogger.info("just an update");
		myLogger.error("example of an error");
		
		
		
	}
}
