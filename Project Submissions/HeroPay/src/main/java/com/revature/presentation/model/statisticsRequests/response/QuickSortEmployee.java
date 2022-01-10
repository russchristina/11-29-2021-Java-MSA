package com.revature.presentation.model.statisticsRequests.response;

import java.util.Objects;

public class QuickSortEmployee {

    private int employeeId;
    private double sum;

    public QuickSortEmployee() {
    }

    public QuickSortEmployee(int employeeId, double sum) {
        this.employeeId = employeeId;
        this.sum = sum;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuickSortEmployee)) return false;
        QuickSortEmployee that = (QuickSortEmployee) o;
        return employeeId == that.employeeId && Double.compare(that.sum, sum) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, sum);
    }

    @Override
    public String toString() {
        return "{\"QuickSortEmployee\":{"
                + "\"employeeId\":\"" + employeeId + "\""
                + ", \"sum\":\"" + sum + "\""
                + "}}";
    }
}
