package com.revature.repository.DTO;

import java.sql.Date;
import java.util.Objects;

public class CompletedRequestEntity {

    private int id;
    private int employeeId;
    private int managerId;
    private boolean status;
    private String response;
    private Date dateResolved;

    public CompletedRequestEntity() {
    }

    public CompletedRequestEntity(int id, int employeeId, int managerId, boolean status, String response, Date dateResolved) {
        this.id = id;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.status = status;
        this.response = response;
        this.dateResolved = dateResolved;
    }

    @Override
    public String toString() {
        return "{\"CompletedRequestEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"employeeId\":\"" + employeeId + "\""
                + ", \"managerId\":\"" + managerId + "\""
                + ", \"status\":\"" + status + "\""
                + ", \"response\":\"" + response + "\""
                + ", \"dateResolved\":" + dateResolved
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompletedRequestEntity)) return false;
        CompletedRequestEntity that = (CompletedRequestEntity) o;
        return getId() == that.getId() && getEmployeeId() == that.getEmployeeId() && getManagerId() == that.getManagerId() && isStatus() == that.isStatus() && Objects.equals(getResponse(), that.getResponse()) && Objects.equals(getDateResolved(), that.getDateResolved());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmployeeId(), getManagerId(), isStatus(), getResponse(), getDateResolved());
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

    public Date getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }
}