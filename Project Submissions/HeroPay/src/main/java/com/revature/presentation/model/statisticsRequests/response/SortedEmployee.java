package com.revature.presentation.model.statisticsRequests.response;

import java.math.BigDecimal;
import java.util.Objects;

public class SortedEmployee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String role;
    private BigDecimal average;
    private BigDecimal sum;

    public SortedEmployee() {
    }

    public SortedEmployee(int employeeId, String firstName, String lastName, String role, BigDecimal average, BigDecimal sum) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.average = average;
        this.sum = sum;
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

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "{\"SortedEmployee\":{"
                + "\"employeeId\":\"" + employeeId + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"role\":\"" + role + "\""
                + ", \"average\":" + average
                + ", \"sum\":" + sum
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortedEmployee)) return false;
        SortedEmployee that = (SortedEmployee) o;
        return employeeId == that.employeeId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(role, that.role) && Objects.equals(average, that.average) && Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, role, average, sum);
    }
}








