package com.revature.models.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventory {
    private int id;
    private int balance;

    @Override
    public String toString() {
        return "{\"Inventory\":{"
                + "\"id\":\"" + id + "\""
                + ", \"balance\":\"" + balance + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory inventory = (Inventory) o;
        return getId() == inventory.getId() && getBalance() == inventory.getBalance();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBalance());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Inventory(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}
