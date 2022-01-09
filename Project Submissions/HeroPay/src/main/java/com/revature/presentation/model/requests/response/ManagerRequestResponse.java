package com.revature.presentation.model.requests.response;

import java.util.Objects;

public class ManagerRequestResponse {

    private int requestId;
    private int employeeId;
    private int managerId;
    private boolean status;
    private String response;

    public ManagerRequestResponse() {
    }

    public ManagerRequestResponse(int requestId, int employeeId, int managerId, boolean status, String response) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.status = status;
        this.response = response;
    }

    @Override
    public String toString() {
        return "{\"ManagerRequestResponse\":{"
                + "\"requestId\":\"" + requestId + "\""
                + ", \"employeeId\":\"" + employeeId + "\""
                + ", \"managerId\":\"" + managerId + "\""
                + ", \"status\":\"" + status + "\""
                + ", \"response\":\"" + response + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManagerRequestResponse)) return false;
        ManagerRequestResponse that = (ManagerRequestResponse) o;
        return getRequestId() == that.getRequestId() && getEmployeeId() == that.getEmployeeId() && getManagerId() == that.getManagerId() && isStatus() == that.isStatus() && Objects.equals(getResponse(), that.getResponse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestId(), getEmployeeId(), getManagerId(), isStatus(), getResponse());
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
}
