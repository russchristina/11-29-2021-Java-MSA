package com.revature.models.shop;

import java.io.Serializable;
import java.util.Objects;

public class Planet implements Serializable {

    private int id;
    private String name;
    private int userId;
    private boolean goldilocksZone;
    private int waterPercent;
    private int averageTemperature;

    public Planet(int id, String name, int userId, boolean goldilocksZone, int waterPercent, int averageTemperature) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.goldilocksZone = goldilocksZone;
        this.waterPercent = waterPercent;
        this.averageTemperature = averageTemperature;
    }

    @Override
    public String toString() {
        return "{\"Planet\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"userId\":\"" + userId + "\""
                + ", \"goldilocksZone\":\"" + goldilocksZone + "\""
                + ", \"waterPercent\":\"" + waterPercent + "\""
                + ", \"averageTemperature\":\"" + averageTemperature + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return getId() == planet.getId() && getUserId() == planet.getUserId() && isGoldilocksZone() == planet.isGoldilocksZone() && getWaterPercent() == planet.getWaterPercent() && getAverageTemperature() == planet.getAverageTemperature() && Objects.equals(getName(), planet.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUserId(), isGoldilocksZone(), getWaterPercent(), getAverageTemperature());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
