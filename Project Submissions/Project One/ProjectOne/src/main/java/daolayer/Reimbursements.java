package daolayer;


import java.sql.Date;

public class Reimbursements {
    private String requestID;
    private int submittedBy;
    private Date submittedDate;
    private int requestAmount;
    private String reason;
    private String status;

    public Reimbursements(String requestID, int submittedBy, Date submittedDate, int requestAmount, String reason, String status) {
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

    public int getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(int submittedBy) {
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

        if (getSubmittedBy() != that.getSubmittedBy()) return false;
        if (getRequestAmount() != that.getRequestAmount()) return false;
        if (getRequestID() != null ? !getRequestID().equals(that.getRequestID()) : that.getRequestID() != null)
            return false;
        if (getSubmittedDate() != null ? !getSubmittedDate().equals(that.getSubmittedDate()) : that.getSubmittedDate() != null)
            return false;
        if (getReason() != null ? !getReason().equals(that.getReason()) : that.getReason() != null) return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getRequestID() != null ? getRequestID().hashCode() : 0;
        result = 31 * result + getSubmittedBy();
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
