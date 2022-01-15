package com.revature.presentation.model.requests;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class PendingRequest {

    private int id;
    private int employeeId;
    private String type;
    private String requestMessage;
    private BigDecimal amount;
    private LocalDate dateSubmission;
    private boolean status;
    private boolean fileUploaded;

    public PendingRequest() {
    }

    public PendingRequest(int id, int employeeId, String type, String requestMessage, BigDecimal amount, LocalDate dateSubmission, boolean status, boolean fileUploaded) {
        this.id = id;
        this.employeeId = employeeId;
        this.type = type;
        this.requestMessage = requestMessage;
        this.amount = amount;
        this.dateSubmission = dateSubmission;
        this.status = status;
        this.fileUploaded = fileUploaded;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDateSubmission() {
        return dateSubmission;
    }

    public void setDateSubmission(LocalDate dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isFileUploaded() {
        return fileUploaded;
    }

    public void setFileUploaded(boolean fileUploaded) {
        this.fileUploaded = fileUploaded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingRequest)) return false;
        PendingRequest that = (PendingRequest) o;
        return id == that.id && employeeId == that.employeeId && status == that.status && fileUploaded == that.fileUploaded && Objects.equals(type, that.type) && Objects.equals(requestMessage, that.requestMessage) && Objects.equals(amount, that.amount) && Objects.equals(dateSubmission, that.dateSubmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, type, requestMessage, amount, dateSubmission, status, fileUploaded);
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
                + ", \"status\":\"" + status + "\""
                + ", \"fileUploaded\":\"" + fileUploaded + "\""
                + "}}";
    }
}
