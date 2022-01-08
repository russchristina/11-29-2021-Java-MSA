package com.revature.presentation.model.requests;

import java.util.Objects;

public class NewRequest {

    private int employeeId;
    private String type;
    private String requestMessage;
    private double amount;

    public NewRequest() {
    }

    public NewRequest(int employeeId, String type, String requestMessage, double amount) {
        this.employeeId = employeeId;
        this.type = type;
        this.requestMessage = requestMessage;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{\"NewRequest\":{"
                + "\"employeeId\":\"" + employeeId + "\""
                + ", \"type\":\"" + type + "\""
                + ", \"requestMessage\":\"" + requestMessage + "\""
                + ", \"amount\":\"" + amount + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewRequest)) return false;
        NewRequest that = (NewRequest) o;
        return getEmployeeId() == that.getEmployeeId() && Double.compare(that.getAmount(), getAmount()) == 0 && Objects.equals(getType(), that.getType()) && Objects.equals(getRequestMessage(), that.getRequestMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getType(), getRequestMessage(), getAmount());
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
}
