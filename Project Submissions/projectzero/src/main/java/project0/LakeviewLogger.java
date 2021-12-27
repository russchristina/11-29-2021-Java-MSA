package project0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LakeviewLogger {

	
	public static void main(String[] args) {
		
		Logger myLogger = LoggerFactory.getLogger(LakeviewLogger.class);
	
		myLogger.debug("debugging an issue at this point in application");
		
		
	}
}
