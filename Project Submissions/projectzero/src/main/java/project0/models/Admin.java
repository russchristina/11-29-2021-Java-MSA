package project0.models;

import java.util.Objects;

public class Admin extends Employee{

private String username;
private String password;


public Admin() {
	super();
	// TODO Auto-generated constructor stub
}
public Admin(String userName, String password, String fullName, int birthMonth, int birthDay, String address,
		String city, String state, String phone, int balance) {
	super(userName, password, fullName, birthMonth, birthDay, address, city, state, phone, balance);
	// TODO Auto-generated constructor stub
}

public Admin(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(password, username);
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	Admin other = (Admin) obj;
	return Objects.equals(password, other.password) && Objects.equals(username, other.username);
}
@Override
public String toString() {
	return "Admin [username=" + username + ", password=" + password + "]";
}



}
