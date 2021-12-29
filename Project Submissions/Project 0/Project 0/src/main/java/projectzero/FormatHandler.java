package projectzero;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatHandler {
	
	public boolean nameFormatCheck(String name) {
		if (Character.isUpperCase(name.charAt(0))) {
			return name.matches("[a-zA-Z]+");
		} else {
			return false;
		} // End else statement
	} // End method
	
	public boolean dateFormatter(String dateOfBirth) {
		String datePattern = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
	    Pattern pat = Pattern.compile(datePattern);
	    Matcher mat = pat.matcher(dateOfBirth);
	    return mat.matches();
	} // End method
	
	public boolean emailFormatter(String email) {
		String emailPattern = "^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailPattern);
        Matcher mat = pat.matcher(email);
        return mat.matches();
	} // End method
	
} // End class
