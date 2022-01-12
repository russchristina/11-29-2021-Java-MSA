package com.revature.service.DTO;

import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.requests.recieve.CompletedRequest;

import java.util.List;
import java.util.Objects;

public class SortedRequests {

    private List<PendingRequest> unansweredRequests;
    private List<PendingRequest> answeredRequests;
    private List<CompletedRequest> completedRequests;

    public SortedRequests(List<PendingRequest> unansweredRequests, List<PendingRequest> answeredRequests, List<CompletedRequest> completedRequests) {
        this.unansweredRequests = unansweredRequests;
        this.answeredRequests = answeredRequests;
        this.completedRequests = completedRequests;
    }

    public SortedRequests() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortedRequests)) return false;
        SortedRequests that = (SortedRequests) o;
        return Objects.equals(getUnansweredRequests(), that.getUnansweredRequests()) && Objects.equals(getAnsweredRequests(), that.getAnsweredRequests()) && Objects.equals(getCompletedRequests(), that.getCompletedRequests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnansweredRequests(), getAnsweredRequests(), getCompletedRequests());
    }

    public List<PendingRequest> getUnansweredRequests() {
        return unansweredRequests;
    }

    public void setUnansweredRequests(List<PendingRequest> unansweredRequests) {
        this.unansweredRequests = unansweredRequests;
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

    @Override
    public String toString() {
        return "{\"SortedPendingRequests\":{"
                + "\"unansweredRequests\":" + unansweredRequests
                + ", \"answeredRequests\":" + answeredRequests
                + ", \"managerResponse\":" + completedRequests
                + "}}";
    }
}
