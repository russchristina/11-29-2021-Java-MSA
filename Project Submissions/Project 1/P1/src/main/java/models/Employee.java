package models;

public class Employee {

	// Fields
	private String name;
	private String password;
	private boolean isManager;
	
	// Constructor
	public Employee(String name, String password, boolean isManager) {
		super();
		this.name = name;
		this.password = password;
		this.isManager = isManager;
	} // End constructor

	// Getters/Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
} // End class
