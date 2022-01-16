package com.revature.presentation.model.employeeRequests;

import java.util.Objects;

public class EmployeeResponse {

    private boolean status;
    private String firstName;
    private String lastName;
    private String role;
    private boolean manager;
    private String JWT;

    public EmployeeResponse(boolean status, String firstName, String lastName, String role, boolean manager, String JWT) {
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.manager = manager;
        this.JWT = JWT;
    }

    public EmployeeResponse() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeResponse)) return false;
        EmployeeResponse that = (EmployeeResponse) o;
        return isStatus() == that.isStatus() && isManager() == that.isManager() && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getRole(), that.getRole()) && Objects.equals(getJWT(), that.getJWT());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isStatus(), getFirstName(), getLastName(), getRole(), isManager(), getJWT());
    }

    @Override
    public String toString() {
        return "{\"EmployeeResponse\":{"
                + "\"status\":\"" + status + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"role\":\"" + role + "\""
                + ", \"manager\":\"" + manager + "\""
                + ", \"JWT\":\"" + JWT + "\""
                + "}}";
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }
}
