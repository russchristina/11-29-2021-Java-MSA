package com.revature.presentation.model.statisticsRequests.response;

import java.util.List;
import java.util.Objects;

public class GeneralStatisticsResponse {

    private SortedAll total;
    private List<SortedType> sortedTypes;
    private List<SortedRole> sortedRoles;


    public GeneralStatisticsResponse() {
    }

    public GeneralStatisticsResponse(SortedAll total, List<SortedType> sortedTypes, List<SortedRole> sortedRoles) {
        this.total = total;
        this.sortedTypes = sortedTypes;
        this.sortedRoles = sortedRoles;
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

    public List<SortedRole> getSortedRoles() {
        return sortedRoles;
    }

    public void setSortedRoles(List<SortedRole> sortedRoles) {
        this.sortedRoles = sortedRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralStatisticsResponse)) return false;
        GeneralStatisticsResponse that = (GeneralStatisticsResponse) o;
        return Objects.equals(total, that.total) && Objects.equals(sortedTypes, that.sortedTypes) && Objects.equals(sortedRoles, that.sortedRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, sortedTypes, sortedRoles);
    }

    @Override
    public String toString() {
        return "{\"GeneralStatisticsResponse\":{"
                + "\"total\":" + total
                + ", \"sortedTypes\":" + sortedTypes
                + ", \"sortedRoles\":" + sortedRoles
                + "}}";
    }
}
