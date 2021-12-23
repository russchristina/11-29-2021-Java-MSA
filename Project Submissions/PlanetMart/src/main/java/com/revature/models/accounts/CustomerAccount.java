package com.revature.models.accounts;

import com.revature.models.shop.Planet;
import com.revature.models.users.User;

import java.util.List;
import java.util.Objects;

public class CustomerAccount {

    private int customerAccountId;
    private int primaryUserId;

    public CustomerAccount(int customerAccountId, int primaryUserId) {
        this.customerAccountId = customerAccountId;
        this.primaryUserId = primaryUserId;
    }

    public int getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(int customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public int getPrimaryUserId() {
        return primaryUserId;
    }

    public void setPrimaryUserId(int primaryUserId) {
        this.primaryUserId = primaryUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerAccount)) return false;
        CustomerAccount that = (CustomerAccount) o;
        return getCustomerAccountId() == that.getCustomerAccountId() && getPrimaryUserId() == that.getPrimaryUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerAccountId(), getPrimaryUserId());
    }

    @Override
    public String toString() {
        return "{\"CustomerAccount\":{"
                + "\"customerAccountId\":\"" + customerAccountId + "\""
                + ", \"primaryUserId\":\"" + primaryUserId + "\""
                + "}}";
    }

    public int getMaxSecondaryAccounts() {
        return 5;
    }

}

