package com.revature.presentation.model.statisticsRequests.response;

import java.util.Objects;

public class SortedRole {

    private String roleName;
    private double meanAverage;
    private double sum;

    @Override
    public String toString() {
        return "{\"sortedRole\":{"
                + "\"roleName\":\"" + roleName + "\""
                + ", \"meanAverage\":\"" + meanAverage + "\""
                + ", \"sum\":\"" + sum + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortedRole)) return false;
        SortedRole that = (SortedRole) o;
        return Double.compare(that.getMeanAverage(), getMeanAverage()) == 0 && Double.compare(that.getSum(), getSum()) == 0 && Objects.equals(getRoleName(), that.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleName(), getMeanAverage(), getSum());
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public double getMeanAverage() {
        return meanAverage;
    }

    public void setMeanAverage(double meanAverage) {
        this.meanAverage = meanAverage;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public SortedRole() {
    }

    public SortedRole(String roleName, double meanAverage, double sum) {
        this.roleName = roleName;
        this.meanAverage = meanAverage;
        this.sum = sum;
    }
}
