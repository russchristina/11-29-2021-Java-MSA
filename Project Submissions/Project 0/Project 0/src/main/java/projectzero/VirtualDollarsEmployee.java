package projectzero;

public class VirtualDollarsEmployee {

	// Fields
	private String employeeName;
	private String password;
	private boolean isAdmin;
	
	// Constructor
	public VirtualDollarsEmployee(String employeeName, String password, boolean isAdmin) {
		this.employeeName = employeeName;
		this.password = password;
		this.isAdmin = isAdmin;
	} // End constructor

	// Getters/setters
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
} // End class
