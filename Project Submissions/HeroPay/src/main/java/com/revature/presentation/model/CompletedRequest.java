package com.revature.presentation.model;

import java.time.LocalDate;
import java.util.Objects;

public class CompletedRequest {

    private int id;
    private int managerId;
    private boolean status;
    private String response;
    private LocalDate dateResolved;

    public CompletedRequest(int id, int managerId, boolean status, String response, LocalDate dateResolved) {
        this.id = id;
        this.managerId = managerId;
        this.status = status;
        this.response = response;
        this.dateResolved = dateResolved;
    }

    public CompletedRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LocalDate getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(LocalDate dateResolved) {
        this.dateResolved = dateResolved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompletedRequest)) return false;
        CompletedRequest that = (CompletedRequest) o;
        return id == that.id && managerId == that.managerId && status == that.status && Objects.equals(response, that.response) && Objects.equals(dateResolved, that.dateResolved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, managerId, status, response, dateResolved);
    }

    @Override
    public String toString() {
        return "{\"CompletedRequest\":{"
                + "\"id\":\"" + id + "\""
                + ", \"managerId\":\"" + managerId + "\""
                + ", \"status\":\"" + status + "\""
                + ", \"response\":\"" + response + "\""
                + ", \"dateResolved\":" + dateResolved
                + "}}";
    }

}
