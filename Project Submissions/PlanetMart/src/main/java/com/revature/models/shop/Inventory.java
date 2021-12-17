package com.revature.models.shop;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Inventory {
    private List<Planet> planetList = new ArrayList<>();

    public Inventory(CustomerAccount account, User user) {

        this.planetList = user.getPlanetList(account, user);
    }

    public Inventory() {

    }

    public List<Planet> getPlanetList() {

        return planetList;
    }

    public void setPlanetList(List<Planet> planetList) {
        this.planetList = planetList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory inventory = (Inventory) o;
        return getPlanetList().equals(inventory.getPlanetList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlanetList());
    }

    @Override
    public String toString() {
        return "{\"Inventory\":{"
                + "\"planetList\":" + planetList
                + "}}";
    }
}
