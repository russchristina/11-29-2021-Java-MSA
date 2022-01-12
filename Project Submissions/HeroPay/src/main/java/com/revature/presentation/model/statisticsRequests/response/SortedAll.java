package com.revature.presentation.model.statisticsRequests.response;

import java.math.BigDecimal;
import java.util.Objects;

public class SortedAll {

    private String total;
    private BigDecimal meanAverage;
    private BigDecimal sum;

    public SortedAll(String total, BigDecimal meanAverage, BigDecimal sum) {
        this.total = total;
        this.meanAverage = meanAverage;
        this.sum = sum;
    }

    public SortedAll() {
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
        if (!(o instanceof SortedAll)) return false;
        SortedAll sortedAll = (SortedAll) o;
        return Objects.equals(total, sortedAll.total) && Objects.equals(meanAverage, sortedAll.meanAverage) && Objects.equals(sum, sortedAll.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, meanAverage, sum);
    }

    @Override
    public String toString() {
        return "{\"SortedAll\":{"
                + "\"total\":\"" + total + "\""
                + ", \"meanAverage\":" + meanAverage
                + ", \"sum\":" + sum
                + "}}";
    }
}
