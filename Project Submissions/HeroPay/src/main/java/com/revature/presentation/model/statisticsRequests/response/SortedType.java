package com.revature.presentation.model.statisticsRequests.response;

import java.math.BigDecimal;
import java.util.Objects;

public class SortedType {

    private String typeName;
    private BigDecimal meanAverage;
    private BigDecimal sum;

    public SortedType() {
    }

    public SortedType(String typeName, BigDecimal meanAverage, BigDecimal sum) {
        this.typeName = typeName;
        this.meanAverage = meanAverage;
        this.sum = sum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        if (!(o instanceof SortedType)) return false;
        SortedType that = (SortedType) o;
        return Objects.equals(typeName, that.typeName) && Objects.equals(meanAverage, that.meanAverage) && Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, meanAverage, sum);
    }

    @Override
    public String toString() {
        return "{\"SortedType\":{"
                + "\"typeName\":\"" + typeName + "\""
                + ", \"meanAverage\":" + meanAverage
                + ", \"sum\":" + sum
                + "}}";
    }
}
