package com.revature.presentation.model.requests.response;

import com.revature.presentation.model.requests.PendingRequest;

import java.util.List;
import java.util.Objects;

public class AllPendingRequestResponse {
    private boolean status;
    private List<PendingRequest> pendingRequests;

    public AllPendingRequestResponse() {
    }

    public AllPendingRequestResponse(boolean status, List<PendingRequest> pendingRequests) {
        this.status = status;
        this.pendingRequests = pendingRequests;
    }

    @Override
    public String toString() {
        return "{\"AllPendingRequestResponse\":{"
                + "\"status\":\"" + status + "\""
                + ", \"pendingRequests\":" + pendingRequests
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AllPendingRequestResponse)) return false;
        AllPendingRequestResponse that = (AllPendingRequestResponse) o;
        return isStatus() == that.isStatus() && Objects.equals(getPendingRequests(), that.getPendingRequests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isStatus(), getPendingRequests());
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
}
