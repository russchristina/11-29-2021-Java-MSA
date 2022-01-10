package com.revature.presentation.model.statisticsRequests.response;

import java.util.List;
import java.util.Objects;

public class GeneralStatisticsResponse {

    private SortedAll total;
    private List<SortedType> sortedTypes;
    private List<SortedRole> sortedRole;


    public GeneralStatisticsResponse() {
    }

    public GeneralStatisticsResponse(SortedAll total, List<SortedType> sortedTypes, List<SortedRole> sortedRole) {
        this.total = total;
        this.sortedTypes = sortedTypes;
        this.sortedRole = sortedRole;
    }

    @Override
    public String toString() {
        return "{\"GeneralStatisticsResponse\":{"
                + "\"total\":" + total
                + ", \"sortedTypes\":" + sortedTypes
                + ", \"sortedRole\":" + sortedRole
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralStatisticsResponse)) return false;
        GeneralStatisticsResponse that = (GeneralStatisticsResponse) o;
        return Objects.equals(getTotal(), that.getTotal()) && Objects.equals(getSortedTypes(), that.getSortedTypes()) && Objects.equals(getSortedRole(), that.getSortedRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotal(), getSortedTypes(), getSortedRole());
    }

    public SortedAll getTotal() {
        return total;
    }

    public void setTotal(SortedAll total) {
        this.total = total;
    }

    public List<SortedType> getSortedTypes() {
        return sortedTypes;
    }

    public void setSortedTypes(List<SortedType> sortedTypes) {
        this.sortedTypes = sortedTypes;
    }

    public List<SortedRole> getSortedRole() {
        return sortedRole;
    }

    public void setSortedRole(List<SortedRole> sortedRole) {
        this.sortedRole = sortedRole;
    }
}
