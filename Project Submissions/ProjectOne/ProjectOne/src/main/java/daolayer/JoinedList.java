package daolayer;

import java.sql.Date;

public class JoinedList {
    private int userID;
    private String firstName;
    private String lastName;
    private String userLogin;
    private boolean admin;
    private String requestID;
    private Date submittedDate;
    private int requestAmount;
    private String reason;
    private String status;

    public JoinedList(){}

    public JoinedList(int userID, String firstName, String lastName, String userLogin, boolean admin, String requestID, Date submittedDate, int requestAmount, String reason, String status) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userLogin = userLogin;
        this.admin = admin;
        this.requestID = requestID;
        this.submittedDate = submittedDate;
        this.requestAmount = requestAmount;
        this.reason = reason;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JoinedList that = (JoinedList) o;

        if (userID != that.userID) return false;
        if (admin != that.admin) return false;
        if (requestAmount != that.requestAmount) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        if (requestID != null ? !requestID.equals(that.requestID) : that.requestID != null) return false;
        if (submittedDate != null ? !submittedDate.equals(that.submittedDate) : that.submittedDate != null)
            return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = userID;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (admin ? 1 : 0);
        result = 31 * result + (requestID != null ? requestID.hashCode() : 0);
        result = 31 * result + (submittedDate != null ? submittedDate.hashCode() : 0);
        result = 31 * result + requestAmount;
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return
                "Status: " + status.toUpperCase() + "|" + " User: ID # " + userID +
                ", " + firstName +" "+ lastName + '\'' +
                "| Username:'" + userLogin + '\'' +
                "| Admin:" + admin +
                "| Request ID:'" + requestID + '\'' +
                "| Submitted Date:" + submittedDate +
                "| Requested Amount: $" + String.format("%,d",requestAmount) +
                "| Reason for request: \"" + reason + "\"" +
                '}' + "\n";
    }
}

