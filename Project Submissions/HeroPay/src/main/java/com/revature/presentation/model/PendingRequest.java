package com.revature.presentation.model;

import java.time.LocalDate;
import java.util.Objects;

public class PendingRequest {

    private int id;
    private int employeeId;
    private String type;
    private String requestMessage;
    private double amount;
    private LocalDate dateSubmission;

    public PendingRequest(int id, int employeeId, String type, String requestMessage, double amount, LocalDate dateSubmission) {
        this.id = id;
        this.employeeId = employeeId;
        this.type = type;
        this.requestMessage = requestMessage;
        this.amount = amount;
        this.dateSubmission = dateSubmission;
    }

    public PendingRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDateSubmission() {
        return dateSubmission;
    }

    public void setDateSubmission(LocalDate dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingRequest)) return false;
        PendingRequest that = (PendingRequest) o;
        return id == that.id && employeeId == that.employeeId && Double.compare(that.amount, amount) == 0 && Objects.equals(type, that.type) && Objects.equals(requestMessage, that.requestMessage) && Objects.equals(dateSubmission, that.dateSubmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, type, requestMessage, amount, dateSubmission);
    }

    @Override
    public String toString() {
        return "{\"PendingRequest\":{"
                + "\"id\":\"" + id + "\""
                + ", \"employeeId\":\"" + employeeId + "\""
                + ", \"type\":\"" + type + "\""
                + ", \"requestMessage\":\"" + requestMessage + "\""
                + ", \"amount\":\"" + amount + "\""
                + ", \"dateSubmission\":" + dateSubmission
                + "}}";
    }
}
