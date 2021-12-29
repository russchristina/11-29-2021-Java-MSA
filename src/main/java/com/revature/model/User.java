package com.revature.model;

import java.util.Objects;

public class User {
private int id;
private String username;
private String userpassword;
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(int id, String username, String userpassword) {
	super();
	this.id = id;
	this.username = username;
	this.userpassword = userpassword;
}

public User(String username, String userpassword) {
	super();
	this.username = username;
	this.userpassword = userpassword;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpassword() {
	return userpassword;
}
public void setUserpassword(String userpassword) {
	this.userpassword = userpassword;
}
@Override
public int hashCode() {
	return Objects.hash(id, username, userpassword);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	return id == other.id && Objects.equals(username, other.username)
			&& Objects.equals(userpassword, other.userpassword);
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", userpassword=" + userpassword + "]";
}

}
