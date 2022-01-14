package daolayer;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(schema = "project_one", name = "hibernate_reimbursements")
public class Reimbursements {
    @Column(name = "request_id")
    @Id @GeneratedValue(generator="status_id_seq")
    @GenericGenerator(name="status_id_seq", strategy = "assigned")
    @SequenceGenerator(allocationSize = 1, name = "status_id_seq", sequenceName = "status_id_seq")
    private String requestID;
    @Column
    private String submittedBy;
    @Column
    private Date submittedDate;
    @Column
    private int requestAmount;
    @Column
    private String reason;
    @Column
    private String status;
//    @ManyToOne
//    private  UserSpecs userSpecs;
    public Reimbursements() {
    }

    public Reimbursements(String requestID, String submittedBy, Date submittedDate, int requestAmount, String reason, String status) {
        this.requestID = requestID;
        this.submittedBy = submittedBy;
        this.submittedDate = submittedDate;
        this.requestAmount = requestAmount;
        this.reason = reason;
        this.status = status;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate (Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public int getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(int requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reimbursements that = (Reimbursements) o;

        if (getRequestAmount() != that.getRequestAmount()) return false;
        if (getRequestID() != null ? !getRequestID().equals(that.getRequestID()) : that.getRequestID() != null)
            return false;
        if (getSubmittedBy() != null ? !getSubmittedBy().equals(that.getSubmittedBy()) : that.getSubmittedBy() != null)
            return false;
        if (getSubmittedDate() != null ? !getSubmittedDate().equals(that.getSubmittedDate()) : that.getSubmittedDate() != null)
            return false;
        if (getReason() != null ? !getReason().equals(that.getReason()) : that.getReason() != null) return false;
        return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getRequestID() != null ? getRequestID().hashCode() : 0;
        result = 31 * result + (getSubmittedBy() != null ? getSubmittedBy().hashCode() : 0);
        result = 31 * result + (getSubmittedDate() != null ? getSubmittedDate().hashCode() : 0);
        result = 31 * result + getRequestAmount();
        result = 31 * result + (getReason() != null ? getReason().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reimbursements{" +
                "requestID='" + requestID + '\'' +
                ", submittedBy=" + submittedBy +
                ", submittedDate='" + submittedDate + '\'' +
                ", requestAmount=" + requestAmount +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
