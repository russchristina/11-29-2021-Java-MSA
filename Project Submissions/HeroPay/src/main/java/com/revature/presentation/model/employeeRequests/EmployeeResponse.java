package com.revature.presentation.model.employeeRequests;

public class EmployeeResponse {

    private boolean status;
    private String firstName;
    private String lastName;
    private String role;
    private boolean manager;

    public EmployeeResponse(boolean status, String firstName, String lastName, String role, boolean manager) {
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.manager = manager;
    }

    public EmployeeResponse() {

    }

    @Override
    public String toString() {
        return "{\"EmployeeResponse\":{"
                + "\"status\":\"" + status + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"role\":\"" + role + "\""
                + ", \"manager\":\"" + manager + "\""
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
}
