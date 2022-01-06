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
    private boolean status;

    public PendingRequestEntity(int id, int employeeId, int requestType, String requestMessage, double amount, Date dateSubmission, boolean status) {
        this.id = id;
        this.employeeId = employeeId;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
        this.amount = amount;
        this.dateSubmission = dateSubmission;
        this.status = status;
    }

    public PendingRequestEntity() {
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
                + ", \"status\":\"" + status + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingRequestEntity)) return false;
        PendingRequestEntity that = (PendingRequestEntity) o;
        return getId() == that.getId() && getEmployeeId() == that.getEmployeeId() && getRequestType() == that.getRequestType() && Double.compare(that.getAmount(), getAmount()) == 0 && isStatus() == that.isStatus() && Objects.equals(getRequestMessage(), that.getRequestMessage()) && Objects.equals(getDateSubmission(), that.getDateSubmission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmployeeId(), getRequestType(), getRequestMessage(), getAmount(), getDateSubmission(), isStatus());
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

    public void setDateSubmission(Date dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
