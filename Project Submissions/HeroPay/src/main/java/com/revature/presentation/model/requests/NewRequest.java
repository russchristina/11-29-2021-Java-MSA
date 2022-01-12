package com.revature.presentation.model.requests;

import java.math.BigDecimal;
import java.util.Objects;

public class NewRequest {

    private int employeeId;
    private String type;
    private String requestMessage;
    private BigDecimal amount;

    public NewRequest() {
    }

    public NewRequest(int employeeId, String type, String requestMessage, BigDecimal amount) {
        this.employeeId = employeeId;
        this.type = type;
        this.requestMessage = requestMessage;
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewRequest)) return false;
        NewRequest that = (NewRequest) o;
        return employeeId == that.employeeId && Objects.equals(type, that.type) && Objects.equals(requestMessage, that.requestMessage) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, type, requestMessage, amount);
    }

    @Override
    public String toString() {
        return "{\"NewRequest\":{"
                + "\"employeeId\":\"" + employeeId + "\""
                + ", \"type\":\"" + type + "\""
                + ", \"requestMessage\":\"" + requestMessage + "\""
                + ", \"amount\":" + amount
                + "}}";
    }
}
