package com.revature.models.accounts;

import com.revature.models.shop.Planet;
import com.revature.models.users.PrimaryUser;
import com.revature.models.users.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomerAccount extends Account{

    private Map<String, User> secondaryUsers;
    private PrimaryUser primaryUser;
    private final int maxSecondaryAccounts = 5;
    public List<Planet> planetsFromDao;

    public CustomerAccount(Map<String, User> secondaryUsers, String username, PrimaryUser primaryUser, List<Planet> planetsFromDao) {
        super(username);
        this.secondaryUsers = secondaryUsers;
        this.primaryUser = primaryUser;
        this.planetsFromDao = planetsFromDao;
    }

    public List<Planet> getPlanetsFromDao() {
        return planetsFromDao;
    }

    private void setPlanetsFromDao(List<Planet> planetsFromDao) {
        this.planetsFromDao = planetsFromDao;
    }


    public CustomerAccount() {
        super();
    }


    public Map<String, User> getSecondaryUsers() {
        return secondaryUsers;
    }

    public void setSecondaryUsers(Map<String, User> secondaryUsers) {
        this.secondaryUsers = secondaryUsers;
    }

    public PrimaryUser getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(PrimaryUser primaryUser) {
        this.primaryUser = primaryUser;
    }

    public int getMaxSecondaryAccounts() {
        return maxSecondaryAccounts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerAccount)) return false;
        CustomerAccount that = (CustomerAccount) o;
        return getMaxSecondaryAccounts() == that.getMaxSecondaryAccounts() && getSecondaryUsers().equals(that.getSecondaryUsers()) && getUsername().equals(that.getUsername()) && getPrimaryUser().equals(that.getPrimaryUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSecondaryUsers(), getUsername(), getPrimaryUser(), getMaxSecondaryAccounts());
    }

    @Override
    public String toString() {
        return "{\"CustomerAccount\":{"
                + "\"secondaryUsers\":" + secondaryUsers
                + ", \"username\":\"" + username + "\""
                + ", \"primaryUser\":" + primaryUser
                + ", \"maxSecondaryAccounts\":\"" + maxSecondaryAccounts + "\""
                + "}}";
    }
}

