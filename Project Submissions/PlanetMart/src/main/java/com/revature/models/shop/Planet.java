package com.revature.models.shop;

import java.io.Serializable;
import java.util.Objects;

public class Planet implements Serializable {

    private int id;
    private int lifeId;
    private String name;
    private int userId;
    private boolean goldilocksZone;
    private int waterPercent;
    private int averageTemperature;
    private int atmosphereId;

    public Planet(int id, int lifeId, String name, int userId, boolean goldilocksZone, int waterPercent, int averageTemperature, int atmosphereId) {
        this.id = id;
        this.lifeId = lifeId;
        this.name = name;
        this.userId = userId;
        this.goldilocksZone = goldilocksZone;
        this.waterPercent = waterPercent;
        this.averageTemperature = averageTemperature;
        this.atmosphereId = atmosphereId;
    }

    @Override
    public String toString() {
        return "{\"Planet\":{"
                + "\"id\":\"" + id + "\""
                + ", \"lifeId\":\"" + lifeId + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"userId\":\"" + userId + "\""
                + ", \"goldilocksZone\":\"" + goldilocksZone + "\""
                + ", \"waterPercent\":\"" + waterPercent + "\""
                + ", \"averageTemperature\":\"" + averageTemperature + "\""
                + ", \"atmosphereId\":\"" + atmosphereId + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return getId() == planet.getId() && getLifeId() == planet.getLifeId() && getUserId() == planet.getUserId() && isGoldilocksZone() == planet.isGoldilocksZone() && getWaterPercent() == planet.getWaterPercent() && getAverageTemperature() == planet.getAverageTemperature() && getAtmosphereId() == planet.getAtmosphereId() && Objects.equals(getName(), planet.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLifeId(), getName(), getUserId(), isGoldilocksZone(), getWaterPercent(), getAverageTemperature(), getAtmosphereId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLifeId() {
        return lifeId;
    }

    public void setLifeId(int lifeId) {
        this.lifeId = lifeId;
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

    public int getAtmosphereId() {
        return atmosphereId;
    }

    public void setAtmosphereId(int atmosphereId) {
        this.atmosphereId = atmosphereId;
    }
}
