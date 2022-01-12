package com.revature.presentation.model.statisticsRequests.response;

import java.math.BigDecimal;
import java.util.Objects;

public class SortedRole {

    private String roleName;
    private BigDecimal meanAverage;
    private BigDecimal sum;

    public SortedRole() {
    }

    public SortedRole(String roleName, BigDecimal meanAverage, BigDecimal sum) {
        this.roleName = roleName;
        this.meanAverage = meanAverage;
        this.sum = sum;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public BigDecimal getMeanAverage() {
        return meanAverage;
    }

    public void setMeanAverage(BigDecimal meanAverage) {
        this.meanAverage = meanAverage;
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
        if (!(o instanceof SortedRole)) return false;
        SortedRole that = (SortedRole) o;
        return Objects.equals(roleName, that.roleName) && Objects.equals(meanAverage, that.meanAverage) && Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, meanAverage, sum);
    }

    @Override
    public String toString() {
        return "{\"SortedRole\":{"
                + "\"roleName\":\"" + roleName + "\""
                + ", \"meanAverage\":" + meanAverage
                + ", \"sum\":" + sum
                + "}}";
    }
}
