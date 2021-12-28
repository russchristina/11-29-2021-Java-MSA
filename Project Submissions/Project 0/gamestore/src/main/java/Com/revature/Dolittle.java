package Com.revature;

public class Dolittle {
	
public static void main(String[] args) {
	
	try {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   System.out.println("Got it!");
		}
		catch(ClassNotFoundException e) {
		   System.out.println("Can't load driver class!");
		}
	
	
	

}
	
}

