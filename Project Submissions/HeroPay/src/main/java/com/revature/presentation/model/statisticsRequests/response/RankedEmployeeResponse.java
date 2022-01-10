package com.revature.presentation.model.statisticsRequests.response;

import java.util.List;
import java.util.Objects;

public class RankedEmployeeResponse {

    private List<QuickSortEmployee> orderedList;

    public RankedEmployeeResponse() {
    }

    public RankedEmployeeResponse(List<QuickSortEmployee> orderedList) {
        this.orderedList = orderedList;
    }

    public List<QuickSortEmployee> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(List<QuickSortEmployee> orderedList) {
        this.orderedList = orderedList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RankedEmployeeResponse)) return false;
        RankedEmployeeResponse that = (RankedEmployeeResponse) o;
        return Objects.equals(orderedList, that.orderedList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderedList);
    }

    @Override
    public String toString() {
        return "{\"RankedEmployeeResponse\":{"
                + "\"orderedList\":" + orderedList
                + "}}";
    }
}
