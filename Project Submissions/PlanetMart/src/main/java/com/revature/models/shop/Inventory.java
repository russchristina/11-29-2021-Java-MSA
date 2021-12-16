package com.revature.models.shop;

import java.util.Map;
import java.util.Objects;

public class Inventory {
    private Map<String, Planet> planetMap;

    public Inventory(Map<String, Planet> planetMap) {
        this.planetMap = planetMap;
    }

    public Inventory() {

    }

    public Map<String, Planet> getPlanetMap() {
        return planetMap;
    }

    public void setPlanetMap(Map<String, Planet> planetMap) {
        this.planetMap = planetMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory inventory = (Inventory) o;
        return getPlanetMap().equals(inventory.getPlanetMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlanetMap());
    }

    @Override
    public String toString() {
        return "{\"Inventory\":{"
                + "\"planetMap\":" + planetMap
                + "}}";
    }
}
