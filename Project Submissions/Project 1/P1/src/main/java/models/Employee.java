package models;

import java.util.Objects;

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

	// Constructor
	public Employee() {}
	
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

	@Override
	public int hashCode() {
		return Objects.hash(isManager, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return isManager == other.isManager && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "Employee name: " + name;
	}
} // End class
