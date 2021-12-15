package com.revature.models;

import com.revature.models.exceptions.RepeatedNameOfUserException;
import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.exceptions.NegativeAmountException;
import com.revature.service.exceptions.EmptyInputException;

public class User {

    private String name;
    private int balance;

    public User(String name, int balance) {
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

    public String changeName(String name) throws EmptyInputException, RepeatedNameOfUserException {
        if(name.trim().contentEquals("") || name.isEmpty()) throw new EmptyInputException("Empty name");
        if(name.contentEquals(this.name)) throw new RepeatedNameOfUserException();
        return this.name = name;
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
