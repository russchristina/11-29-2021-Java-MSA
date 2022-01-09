package com.revature.presentation.model.statisticsRequests.response;

import java.util.List;
import java.util.Objects;

public class generalStatisticsResponse {

    private SortedAll total;
    private List<SortedType> sortedTypes;
    private List<SortedRole> sortedRole;

    public generalStatisticsResponse() {
    }

    public generalStatisticsResponse(SortedAll total, List<SortedType> sortedTypes, List<SortedRole> sortedRole) {
        this.total = total;
        this.sortedTypes = sortedTypes;
        this.sortedRole = sortedRole;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof generalStatisticsResponse)) return false;
        generalStatisticsResponse that = (generalStatisticsResponse) o;
        return Objects.equals(total, that.total) && Objects.equals(sortedTypes, that.sortedTypes) && Objects.equals(sortedRole, that.sortedRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, sortedTypes, sortedRole);
    }

    @Override
    public String toString() {
        return "{\"generalStatisticsResponse\":{"
                + "\"total\":" + total
                + ", \"sortedTypes\":" + sortedTypes
                + ", \"sortedRole\":" + sortedRole
                + "}}";
    }
}
