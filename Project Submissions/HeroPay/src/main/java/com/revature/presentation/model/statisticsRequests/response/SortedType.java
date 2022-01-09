package com.revature.presentation.model.statisticsRequests.response;

import java.util.Objects;

public class SortedType {

    private String typeName;
    private double meanAverage;
    private double sum;

    public SortedType() {
    }

    public SortedType(String typeName, double meanAverage, double sum) {
        this.typeName = typeName;
        this.meanAverage = meanAverage;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "{\"sortedType\":{"
                + "\"typeName\":\"" + typeName + "\""
                + ", \"meanAverage\":\"" + meanAverage + "\""
                + ", \"sum\":\"" + sum + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortedType)) return false;
        SortedType that = (SortedType) o;
        return Double.compare(that.getMeanAverage(), getMeanAverage()) == 0 && Double.compare(that.getSum(), getSum()) == 0 && Objects.equals(getTypeName(), that.getTypeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeName(), getMeanAverage(), getSum());
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
}
