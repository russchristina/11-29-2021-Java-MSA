package com.revature.presentation.model.requests.response;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;

import java.util.List;
import java.util.Objects;

public class AllRequestResponse {


    private List<PendingRequest> pendingRequests;
    private List<PendingRequest> answeredRequests;
    private List<CompletedRequest> completedRequests;

    public AllRequestResponse(boolean status, List<PendingRequest> pendingRequests, List<PendingRequest> answeredRequests, List<CompletedRequest> completedRequests) {
        this.pendingRequests = pendingRequests;
        this.answeredRequests = answeredRequests;
        this.completedRequests = completedRequests;
    }

    public AllRequestResponse() {
    }

    @Override
    public String toString() {
        return "{\"AllRequestResponse\":{"
                + "\"pendingRequests\":" + pendingRequests
                + ", \"answeredRequests\":" + answeredRequests
                + ", \"completedRequests\":" + completedRequests
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AllRequestResponse)) return false;
        AllRequestResponse that = (AllRequestResponse) o;
        return Objects.equals(getPendingRequests(), that.getPendingRequests()) && Objects.equals(getAnsweredRequests(), that.getAnsweredRequests()) && Objects.equals(getCompletedRequests(), that.getCompletedRequests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPendingRequests(), getAnsweredRequests(), getCompletedRequests());
    }

    public List<PendingRequest> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(List<PendingRequest> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public List<PendingRequest> getAnsweredRequests() {
        return answeredRequests;
    }

    public void setAnsweredRequests(List<PendingRequest> answeredRequests) {
        this.answeredRequests = answeredRequests;
    }

    public List<CompletedRequest> getCompletedRequests() {
        return completedRequests;
    }

    public void setCompletedRequests(List<CompletedRequest> completedRequests) {
        this.completedRequests = completedRequests;
    }
}
