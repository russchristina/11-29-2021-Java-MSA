package com.revature.models.users;

import java.util.Objects;

public class CustomerUser {
    private int userId;
    private String user_name;
    private int inventoryId;
    private int customerAccountId;

    public CustomerUser(int userId, String user_name, int inventoryId, int customerAccountId) {
        this.userId = userId;
        this.user_name = user_name;
        this.inventoryId = inventoryId;
        this.customerAccountId = customerAccountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(int customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerUser)) return false;
        CustomerUser that = (CustomerUser) o;
        return getUserId() == that.getUserId() && getInventoryId() == that.getInventoryId() && getCustomerAccountId() == that.getCustomerAccountId() && Objects.equals(getUser_name(), that.getUser_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUser_name(), getInventoryId(), getCustomerAccountId());
    }

    @Override
    public String toString() {
        return "{\"CustomerUser\":{"
                + "\"userId\":\"" + userId + "\""
                + ", \"user_name\":\"" + user_name + "\""
                + ", \"inventoryId\":\"" + inventoryId + "\""
                + ", \"customerAccountId\":\"" + customerAccountId + "\""
                + "}}";
    }
}
