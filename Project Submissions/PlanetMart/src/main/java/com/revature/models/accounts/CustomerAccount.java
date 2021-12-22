package com.revature.models.accounts;

import com.revature.models.shop.Planet;
import com.revature.models.users.PrimaryUser;
import com.revature.models.users.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomerAccount extends Account{

    private List<User> users;
    private int primaryUserId;
    public List<Planet> planets;

    public CustomerAccount(int id, String username, List<User> users,int primaryUserId, List<Planet> planets) {
        super(id, username);
        this.users = users;
        this.primaryUserId = primaryUserId;
        this.planets = planets;
    }

    public CustomerAccount() {
        super();
    }

    public int getPrimaryUserId() {
        return primaryUserId;
    }

    public void setPrimaryUserId(int primaryUserId) {
        this.primaryUserId = primaryUserId;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    private void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public int getMaxSecondaryAccounts() {
        return 5;
    }

}

