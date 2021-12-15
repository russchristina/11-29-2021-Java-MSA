package com.revature.models;

import java.util.Objects;

public class Account {
    private int balance;
    private String name;
    private char type;

    public Account(int balance, String name, char type) {
        this.balance = balance;
        this.name = name;
        this.type = type;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getBalance() == account.getBalance() && getType() == account.getType() && getName().equals(account.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalance(), getName(), getType());
    }

    @Override
    public String toString() {
        return "{\"Account\":{"
                + "\"balance\":\"" + balance + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"type\":\"" + type + "\""
                + "}}";
    }
}
