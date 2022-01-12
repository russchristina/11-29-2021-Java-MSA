package com.revature.presentation.model.statisticsRequests.response;

import java.math.BigDecimal;
import java.util.Objects;

public class QuickSortEmployee {

    private int employeeId;
    private BigDecimal sum;

    public QuickSortEmployee() {
    }

    public QuickSortEmployee(int employeeId, BigDecimal sum) {
        this.employeeId = employeeId;
        this.sum = sum;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuickSortEmployee)) return false;
        QuickSortEmployee that = (QuickSortEmployee) o;
        return employeeId == that.employeeId && Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, sum);
    }

    @Override
    public String toString() {
        return "{\"QuickSortEmployee\":{"
                + "\"employeeId\":\"" + employeeId + "\""
                + ", \"sum\":" + sum
                + "}}";
    }
}
