package com.revature.presentation.model.loginRequests;

import java.util.Objects;

public class LoginResponse {

    private boolean status;
    private int employeeId;

    public LoginResponse(boolean status, int employeeId) {
        this.status = status;
        this.employeeId = employeeId;
    }

    public LoginResponse() {

    }

    @Override
    public String toString() {
        return "{\"LoginResponse\":{"
                + "\"status\":\"" + status + "\""
                + ", \"employeeId\":\"" + employeeId + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginResponse)) return false;
        LoginResponse that = (LoginResponse) o;
        return isStatus() == that.isStatus() && getEmployeeId() == that.getEmployeeId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isStatus(), getEmployeeId());
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
