package com.revature.models;

import java.util.List;

public class CustomerAccount extends Account{

    private List<User> secondaryUsers;
    private PrimaryUser primaryUser;

    public CustomerAccount(List<User> secondaryUsers, PrimaryUser primaryUser) {
        this.secondaryUsers = secondaryUsers;
        this.primaryUser = primaryUser;
    }

    public CustomerAccount() {

    }


}

