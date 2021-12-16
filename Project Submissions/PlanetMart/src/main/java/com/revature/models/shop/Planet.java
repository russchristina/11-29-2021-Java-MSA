package com.revature.models.shop;

import com.revature.models.accounts.Account;
import com.revature.models.users.User;

import java.util.Objects;

public class Planet {

    private int cost;
    private String name;
    private User owner;
    private Account account;

    public Planet(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public Planet() {
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return getCost() == planet.getCost() && getName().equals(planet.getName()) && getOwner().equals(planet.getOwner()) && getAccount().equals(planet.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCost(), getName(), getOwner(), getAccount());
    }

    @Override
    public String toString() {
        return "{\"Planet\":{"
                + "\"cost\":\"" + cost + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"owner\":" + owner
                + ", \"account\":" + account
                + "}}";
    }
}
