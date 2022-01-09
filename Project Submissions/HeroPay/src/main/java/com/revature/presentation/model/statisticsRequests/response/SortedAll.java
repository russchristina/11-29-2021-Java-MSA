package com.revature.presentation.model.statisticsRequests.response;

import java.util.Objects;

public class SortedAll {

    private String total;
    private double meanAverage;
    private double sum;

    public SortedAll(String total, double meanAverage, double sum) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortedAll)) return false;
        SortedAll sortedAll = (SortedAll) o;
        return Double.compare(sortedAll.meanAverage, meanAverage) == 0 && Double.compare(sortedAll.sum, sum) == 0 && Objects.equals(total, sortedAll.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, meanAverage, sum);
    }

    @Override
    public String toString() {
        return "{\"sortedAll\":{"
                + "\"total\":\"" + total + "\""
                + ", \"meanAverage\":\"" + meanAverage + "\""
                + ", \"sum\":\"" + sum + "\""
                + "}}";
    }
}
