package com.revature.models.shop;

import com.revature.models.accounts.Account;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Planet implements Serializable {

    private Life lifeForm;
    private int cost;
    private String name;
    private User owner;
    private String username;
    private boolean goldilocksZone;
    private int waterPercent;
    private int averageTemperature;
    private Map<String, Integer> atmosphere;


    public Planet(int cost, String name, User owner, String username) {
        this.cost = cost;
        this.name = name;
        this.owner = owner;
        this.username = username;
    }

    public Planet() {
    }


    public Planet(String name, boolean goldiLocksZone, int waterPercent, int averageTemperature, Map<String, Integer> atmosphere, Life lifeForm, int cost, User owner, String username) {
        this.name = name;
        this.goldilocksZone = goldiLocksZone;
        this.waterPercent = waterPercent;
        this.averageTemperature = averageTemperature;
        this.atmosphere = atmosphere;
        this.lifeForm = lifeForm;
        this.cost = cost;
        this.owner = owner;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return getCost() == planet.getCost() && isGoldilocksZone() == planet.isGoldilocksZone() && getWaterPercent() == planet.getWaterPercent() && getAverageTemperature() == planet.getAverageTemperature() && Objects.equals(getLifeForm(), planet.getLifeForm()) && getName().equals(planet.getName()) && Objects.equals(getOwner(), planet.getOwner()) && Objects.equals(getUsername(), planet.getUsername()) && getAtmosphere().equals(planet.getAtmosphere());
    }

    @Override
    public String toString() {
        return "{\"Planet\":{"
                + "\"lifeForm\":" + lifeForm
                + ", \"cost\":\"" + cost + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"owner\":" + owner
                + ", \"username\":\"" + username + "\""
                + ", \"goldilocksZone\":\"" + goldilocksZone + "\""
                + ", \"waterPercent\":\"" + waterPercent + "\""
                + ", \"averageTemperature\":\"" + averageTemperature + "\""
                + ", \"atmosphere\":" + atmosphere
                + "}}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLifeForm(), getCost(), getName(), getOwner(), getUsername(), isGoldilocksZone(), getWaterPercent(), getAverageTemperature(), getAtmosphere());
    }

    public Life getLifeForm() {
        return lifeForm;
    }

    public void setLifeForm(Life lifeForm) {
        this.lifeForm = lifeForm;
    }

    public boolean isGoldilocksZone() {
        return goldilocksZone;
    }

    public void setGoldilocksZone(boolean goldilocksZone) {
        this.goldilocksZone = goldilocksZone;
    }

    public int getWaterPercent() {
        return waterPercent;
    }

    public void setWaterPercent(int waterPercent) {
        this.waterPercent = waterPercent;
    }

    public int getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(int averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public Map<String, Integer> getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Map<String, Integer> atmosphere) {
        this.atmosphere = atmosphere;
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
