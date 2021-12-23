package com.revature.models.accounts;

import com.revature.models.users.User;

import java.util.List;
import java.util.Objects;

public class EmployeeAccount {

    private int employeeId;
    private int userId;
    private int adminId;

    public EmployeeAccount(int employeeId, int userId, int adminId) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.adminId = adminId;
    }

    public EmployeeAccount() {
    }

    @Override
    public String toString() {
        return "{\"EmployeeAccount\":{"
                + "\"employeeId\":\"" + employeeId + "\""
                + ", \"userId\":\"" + userId + "\""
                + ", \"adminId\":\"" + adminId + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeAccount)) return false;
        EmployeeAccount that = (EmployeeAccount) o;
        return getEmployeeId() == that.getEmployeeId() && getUserId() == that.getUserId() && getAdminId() == that.getAdminId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getUserId(), getAdminId());
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public List<CustomerAccount> getCustomerAccounts(){
        //DOA Interaction = Read
        return null;
    }

    public void deleteCustomerAccount(CustomerAccount account){
        //DOA Interaction - Delete
    }

    public List<User> getUsersFromAccount(CustomerAccount account){
        //DOA Interaction = Read
        return null;
    }


}
