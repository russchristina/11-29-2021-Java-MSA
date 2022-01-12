package com.revature.repository.DTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "pending_request", schema = "project_1")
public class PendingRequestEntity {

    @Id
    @Column(name = "pending_request_id")
    @GeneratedValue(generator="pending_request_id_seq", strategy=GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "pending_request_id_seq", sequenceName = "pending_request_id_seq")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeAccountEntity employeeAccount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type")
    private RequestTypeEntity requestType;
    @Column(name = "request_message")
    private String requestMessage;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date_submission")
    private Date dateSubmission;
    @Column(name = "status")
    private boolean status;

    public PendingRequestEntity() {
    }

    public PendingRequestEntity(int id, EmployeeAccountEntity employeeAccount, RequestTypeEntity requestType, String requestMessage, BigDecimal amount, Date dateSubmission, boolean status) {
        this.id = id;
        this.employeeAccount = employeeAccount;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
        this.amount = amount;
        this.dateSubmission = dateSubmission;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EmployeeAccountEntity getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(EmployeeAccountEntity employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public RequestTypeEntity getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestTypeEntity requestType) {
        this.requestType = requestType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingRequestEntity)) return false;
        PendingRequestEntity that = (PendingRequestEntity) o;
        return id == that.id && status == that.status && Objects.equals(employeeAccount, that.employeeAccount) && Objects.equals(requestType, that.requestType) && Objects.equals(requestMessage, that.requestMessage) && Objects.equals(amount, that.amount) && Objects.equals(dateSubmission, that.dateSubmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeAccount, requestType, requestMessage, amount, dateSubmission, status);
    }

    @Override
    public String toString() {
        return "{\"PendingRequestEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"employeeAccount\":" + employeeAccount
                + ", \"requestType\":" + requestType
                + ", \"requestMessage\":\"" + requestMessage + "\""
                + ", \"amount\":\"" + amount + "\""
                + ", \"dateSubmission\":" + dateSubmission
                + ", \"status\":\"" + status + "\""
                + "}}";
    }
}
