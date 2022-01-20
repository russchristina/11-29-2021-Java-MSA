package com.revature.repo;

import java.util.Objects;

public class LoginUserInfo {
	private String username;
	private int employeeid;
	private String password;
	private int id;
	
public LoginUserInfo(String username, int employeeid, String password, int id) {
	this.username = username;
	this.employeeid =employeeid;
	this.password = password;
	this.id = id;
}

public String retreiveUsername() {
	return username;		
}

public void establishUsername(String username) {
	this.username = username;
}

public int retreiveEmployeeid() {
	return employeeid;
}

public void establishEmployeeid(int employeeid) {
	this.employeeid = employeeid;
}

public String retreivePassword() {
	return password;
}

public void establishPassword(String password) {
	this.password = password;
}

public int retreiveId() {
	return id;
}

public void establishId(int id) {
	this.id = id;
}
@Override
public int hashCode() {
    return Objects.hash(username, password, id, employeeid);
}

@Override
public String toString() {
    return "{\"LoginUserInfo\":{"
            + "\"password\":\"" + password + "\""
            + ", \"username\":\"" + username + "\""
             + ", \"id\":\"" + id + "\""
              + ", \"employeeid\":\"" + employeeid + "\"" 
            + "}}";
}


@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof LoginUserInfo)) return false;
  LoginUserInfo login = (LoginUserInfo) o;
    return Objects.equals(password, login.password) && Objects.equals(username, login.username) && Objects.equals(id, login.id)
    		&& Objects.equals(employeeid, login.employeeid);
}
}
