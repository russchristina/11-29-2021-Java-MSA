package com.revature.presentation.model.requests;

import java.util.List;
import java.util.Objects;

public class AllRequestResponse {

    private boolean status;
    private List<PendingRequest> pendingRequests;
    private List<CompletedRequest> completedRequests;

    public AllRequestResponse(boolean status, List<PendingRequest> pendingRequests, List<CompletedRequest> completedRequests) {
        this.status = status;
        this.pendingRequests = pendingRequests;
        this.completedRequests = completedRequests;
    }

    public AllRequestResponse() {
    }

    @Override
    public String toString() {
        return "{\"AllRequestResponse\":{"
                + "\"status\":\"" + status + "\""
                + ", \"pendingRequests\":" + pendingRequests
                + ", \"completedRequests\":" + completedRequests
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AllRequestResponse)) return false;
        AllRequestResponse that = (AllRequestResponse) o;
        return isStatus() == that.isStatus() && Objects.equals(getPendingRequests(), that.getPendingRequests()) && Objects.equals(getCompletedRequests(), that.getCompletedRequests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isStatus(), getPendingRequests(), getCompletedRequests());
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<PendingRequest> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(List<PendingRequest> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public List<CompletedRequest> getCompletedRequests() {
        return completedRequests;
    }

    public void setCompletedRequests(List<CompletedRequest> completedRequests) {
        this.completedRequests = completedRequests;
    }
}
