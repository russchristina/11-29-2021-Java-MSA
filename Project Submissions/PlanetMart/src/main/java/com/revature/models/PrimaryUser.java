package com.revature.models;

public class PrimaryUser extends User{

    public PrimaryUser(String name, int balance) {
        super(name, balance);
    }

    public PrimaryUser() {
        super();
    }

    public User addSecondaryUser(String name, Account account){


    }

    public void transferFundsToUser(int amount, User user){

    }

    public void transferFundsFromUser(int amount, User user){

    }

}
