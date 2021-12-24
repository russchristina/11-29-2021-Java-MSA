package com.revature.models.accounts;

import java.util.Objects;

public class CustomerAccount {

    private int customerAccountId;
    private int userCredentialId;
    private int primaryUserId;

    public CustomerAccount(int customerAccountId, int userCredentialId, int primaryUserId) {
        this.customerAccountId = customerAccountId;
        this.userCredentialId = userCredentialId;
        this.primaryUserId = primaryUserId;
    }

    @Override
    public String toString() {
        return "{\"CustomerAccount\":{"
                + "\"customerAccountId\":\"" + customerAccountId + "\""
                + ", \"userCredentialId\":\"" + userCredentialId + "\""
                + ", \"primaryUserId\":\"" + primaryUserId + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerAccount)) return false;
        CustomerAccount that = (CustomerAccount) o;
        return getCustomerAccountId() == that.getCustomerAccountId() && getUserCredentialId() == that.getUserCredentialId() && getPrimaryUserId() == that.getPrimaryUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerAccountId(), getUserCredentialId(), getPrimaryUserId());
    }

    public int getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(int customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public int getUserCredentialId() {
        return userCredentialId;
    }

    public void setUserCredentialId(int userCredentialId) {
        this.userCredentialId = userCredentialId;
    }

    public int getPrimaryUserId() {
        return primaryUserId;
    }

    public void setPrimaryUserId(int primaryUserId) {
        this.primaryUserId = primaryUserId;
    }

    public int getMaxSecondaryAccounts() {
        return 5;
    }

}

