package com.revature.models.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventory {

    private List<Planet> planetOwnedList = new ArrayList<>();

    public Inventory(List<Planet> planetOwnedList) {
        this.planetOwnedList = planetOwnedList;
    }

    public Inventory() {

    }

    public List<Planet> getPlanetOwnedList() {
        return planetOwnedList;
    }

    public void setPlanetOwnedList(List<Planet> planetList) {
        this.planetOwnedList = planetOwnedList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory inventory = (Inventory) o;
        return getPlanetOwnedList().equals(inventory.getPlanetOwnedList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlanetOwnedList());
    }

    @Override
    public String toString() {
        return "{\"Inventory\":{"
                + "\"planetList\":" + planetOwnedList
                + "}}";
    }
}
