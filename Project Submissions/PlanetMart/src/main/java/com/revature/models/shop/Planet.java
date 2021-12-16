package com.revature.models.shop;

import com.revature.models.accounts.Account;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;

import java.util.Objects;

public class Planet {

    private int cost;
    private String name;
    private User owner;
    private String username;

    public Planet(int cost, String name, User owner, String username) {
        this.cost = cost;
        this.name = name;
        this.owner = owner;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
