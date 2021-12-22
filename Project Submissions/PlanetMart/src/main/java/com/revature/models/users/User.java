package com.revature.models.users;

import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.exceptions.NegativeAmountException;

public class User {


    protected int id;
    protected String name;
    protected int balance;

    public User(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public User() {
    }

    public int addFunds(int amount) throws NegativeAmountException {
        if(amount < 0) throw new NegativeAmountException();
        return balance += amount;
    }

    public int removeFunds(int amount) throws NegativeAmountException, InsufficientFundsException {
        if(amount <= 0) throw new NegativeAmountException();
        if(amount > balance) throw new InsufficientFundsException("balance is less than amount removing");
        return balance -= amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
