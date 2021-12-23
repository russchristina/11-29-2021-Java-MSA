package com.revature.models.users;

public class User {

    private int userId;
    private String name;
    private int inventoryId;
    private int customerAccountId;

    public User(int userId, String name, int inventoryId, int customerAccountId) {
        this.userId = userId;
        this.name = name;
        this.inventoryId = inventoryId;
        this.customerAccountId = customerAccountId;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

}
