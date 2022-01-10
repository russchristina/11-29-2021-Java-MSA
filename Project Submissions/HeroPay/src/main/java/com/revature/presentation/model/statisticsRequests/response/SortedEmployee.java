package com.revature.presentation.model.statisticsRequests.response;

import java.util.Objects;

public class SortedEmployee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String role;
    private double average;
    private double sum;

    public SortedEmployee() {
    }

    public SortedEmployee(int employeeId, String firstName, String lastName, String role, double average, double sum) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.average = average;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "{\"SortedEmployee\":{"
                + "\"employeeId\":\"" + employeeId + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"role\":\"" + role + "\""
                + ", \"average\":\"" + average + "\""
                + ", \"sum\":\"" + sum + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortedEmployee)) return false;
        SortedEmployee that = (SortedEmployee) o;
        return getEmployeeId() == that.getEmployeeId() && Double.compare(that.getAverage(), getAverage()) == 0 && Double.compare(that.getSum(), getSum()) == 0 && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getFirstName(), getLastName(), getRole(), getAverage(), getSum());
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}








