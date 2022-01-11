package com.revature.repository.DTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name="completed_request", schema = "project_1")
public class CompletedRequestEntity {


    @OneToOne
    @JoinColumn(name = "completed_request_id")
    private PendingRequestEntity pendingRequest;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeAccountEntity employeeAccount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private EmployeeAccountEntity managerAccount;
    @Column(name = "status")
    private boolean status;
    @Column(name = "response")
    private String response;
    @Column(name = "date_resolved")
    private Date dateResolved;
    @Id
    @Column(name = "unique_id")
    @GeneratedValue(generator = "completed_request_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "completed_request_id_seq", sequenceName = "completed_request_unique_id_seq")
    private int requestId;

    public CompletedRequestEntity() {
    }

    public CompletedRequestEntity(PendingRequestEntity pendingRequest, EmployeeAccountEntity employeeAccount, EmployeeAccountEntity managerAccount, boolean status, String response, Date dateResolved, int requestId) {
        this.pendingRequest = pendingRequest;
        this.employeeAccount = employeeAccount;
        this.managerAccount = managerAccount;
        this.status = status;
        this.response = response;
        this.dateResolved = dateResolved;
        this.requestId = requestId;
    }

    public PendingRequestEntity getPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(PendingRequestEntity pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    public EmployeeAccountEntity getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(EmployeeAccountEntity employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public EmployeeAccountEntity getManagerAccount() {
        return managerAccount;
    }

    public void setManagerAccount(EmployeeAccountEntity managerAccount) {
        this.managerAccount = managerAccount;
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

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompletedRequestEntity)) return false;
        CompletedRequestEntity that = (CompletedRequestEntity) o;
        return status == that.status && requestId == that.requestId && Objects.equals(pendingRequest, that.pendingRequest) && Objects.equals(employeeAccount, that.employeeAccount) && Objects.equals(managerAccount, that.managerAccount) && Objects.equals(response, that.response) && Objects.equals(dateResolved, that.dateResolved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pendingRequest, employeeAccount, managerAccount, status, response, dateResolved, requestId);
    }

    @Override
    public String toString() {
        return "{\"CompletedRequestEntity\":{"
                + "\"pendingRequest\":" + pendingRequest
                + ", \"employeeAccount\":" + employeeAccount
                + ", \"managerAccount\":" + managerAccount
                + ", \"status\":\"" + status + "\""
                + ", \"response\":\"" + response + "\""
                + ", \"dateResolved\":" + dateResolved
                + ", \"requestId\":\"" + requestId + "\""
                + "}}";
    }
}
