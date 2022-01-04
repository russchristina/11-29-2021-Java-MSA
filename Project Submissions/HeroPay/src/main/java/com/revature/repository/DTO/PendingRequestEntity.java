package com.revature.repository.DTO;

import java.sql.Date;
import java.util.Objects;

public class PendingRequestEntity {

    private int id;
    private int employeeId;
    private int requestType;
    private String requestMessage;
    private double amount;
    private Date dateSubmission;

    public PendingRequestEntity(int id, int employeeId, int requestType, String requestMessage, double amount, java.sql.Date dateSubmission) {
        this.id = id;
        this.employeeId = employeeId;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
        this.amount = amount;
        this.dateSubmission = dateSubmission;
    }

    public PendingRequestEntity() {
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

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
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

    public Date getDateSubmission() {
        return dateSubmission;
    }

    public void setDateSubmission(java.sql.Date dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingRequestEntity)) return false;
        PendingRequestEntity that = (PendingRequestEntity) o;
        return id == that.id && employeeId == that.employeeId && requestType == that.requestType && Double.compare(that.amount, amount) == 0 && Objects.equals(requestMessage, that.requestMessage) && Objects.equals(dateSubmission, that.dateSubmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, requestType, requestMessage, amount, dateSubmission);
    }

    @Override
    public String toString() {
        return "{\"PendingRequestEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"employeeId\":\"" + employeeId + "\""
                + ", \"requestType\":\"" + requestType + "\""
                + ", \"requestMessage\":\"" + requestMessage + "\""
                + ", \"amount\":\"" + amount + "\""
                + ", \"dateSubmission\":" + dateSubmission
                + "}}";
    }
}
