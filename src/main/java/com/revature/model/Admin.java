package com.revature.model;

import java.util.Objects;

public class Admin {
private String AdminUsername;
private String AdminPassword;
public Admin() {
	super();
	// TODO Auto-generated constructor stub
}
public Admin(String adminUsername, String adminPassword) {
	super();
	AdminUsername = adminUsername;
	AdminPassword = adminPassword;
}
public String getAdminUsername() {
	return AdminUsername;
}
public void setAdminUsername(String adminUsername) {
	AdminUsername = adminUsername;
}
public String getAdminPassword() {
	return AdminPassword;
}
public void setAdminPassword(String adminPassword) {
	AdminPassword = adminPassword;
}
@Override
public int hashCode() {
	return Objects.hash(AdminPassword, AdminUsername);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Admin other = (Admin) obj;
	return Objects.equals(AdminPassword, other.AdminPassword) && Objects.equals(AdminUsername, other.AdminUsername);
}
@Override
public String toString() {
	return "Admin [AdminUsername=" + AdminUsername + ", AdminPassword=" + AdminPassword + "]";
}

}
