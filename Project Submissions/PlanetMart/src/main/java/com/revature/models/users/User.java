package com.revature.models.users;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.shop.Inventory;

public class User {

    protected String name;
    protected int balance;
    protected Inventory inventory;
    protected String primaryUsername;

    public User(String name, int balance, Inventory inventory, String primaryUsername) {
        this.name = name;
        this.balance = balance;
        this.inventory = inventory;
        this.primaryUsername = primaryUsername;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public String getPrimaryUsername() {
        return primaryUsername;
    }

    public void setPrimaryUsername(String primaryUsername) {
        this.primaryUsername = primaryUsername;
    }
}
