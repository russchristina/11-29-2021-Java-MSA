package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerAccount extends Account{

    private Map<String, User> secondaryUsers;
    private String username;
    private PrimaryUser primaryUser;
    private int maxSecondaryAccounts = 5;

    public CustomerAccount(Map<String, User> secondaryUsers, String username, PrimaryUser primaryUser) {
        this.secondaryUsers = secondaryUsers;
        this.username = username;
        this.primaryUser = primaryUser;
    }

    public CustomerAccount() {
    }

    public Map<String, User> getSecondaryUsers() {
        return secondaryUsers;
    }

    public void setSecondaryUsers(Map<String, User> secondaryUsers) {
        this.secondaryUsers = secondaryUsers;
    }

    public PrimaryUser getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(PrimaryUser primaryUser) {
        this.primaryUser = primaryUser;
    }

    public int getMaxSecondaryAccounts() {
        return maxSecondaryAccounts;
    }
}

