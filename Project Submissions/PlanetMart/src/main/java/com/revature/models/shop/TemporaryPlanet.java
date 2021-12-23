package com.revature.models.shop;

import java.util.Map;
import java.util.Objects;

public class TemporaryPlanet {

    private String name;
    private Life lifeform;
    private int userId;
    private boolean goldilocksZone;
    private int waterPercent;
    private int averageTemperature;
    private Map<String, Integer> atmosphere;

    public TemporaryPlanet(String name, Life lifeform, int userId, boolean goldilocksZone, int waterPercent, int averageTemperature, Map<String, Integer> atmosphere) {
        this.name = name;
        this.lifeform = lifeform;
        this.userId = userId;
        this.goldilocksZone = goldilocksZone;
        this.waterPercent = waterPercent;
        this.averageTemperature = averageTemperature;
        this.atmosphere = atmosphere;
    }

    @Override
    public String toString() {
        return "{\"TemporaryPlanet\":{"
                + "\"name\":\"" + name + "\""
                + ", \"lifeform\":" + lifeform
                + ", \"userId\":\"" + userId + "\""
                + ", \"goldilocksZone\":\"" + goldilocksZone + "\""
                + ", \"waterPercent\":\"" + waterPercent + "\""
                + ", \"averageTemperature\":\"" + averageTemperature + "\""
                + ", \"atmosphere\":" + atmosphere
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemporaryPlanet)) return false;
        TemporaryPlanet that = (TemporaryPlanet) o;
        return getUserId() == that.getUserId() && isGoldilocksZone() == that.isGoldilocksZone() && getWaterPercent() == that.getWaterPercent() && getAverageTemperature() == that.getAverageTemperature() && Objects.equals(getName(), that.getName()) && Objects.equals(getLifeform(), that.getLifeform()) && Objects.equals(getAtmosphere(), that.getAtmosphere());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLifeform(), getUserId(), isGoldilocksZone(), getWaterPercent(), getAverageTemperature(), getAtmosphere());
    }

    public Life getLifeform() {
        return lifeform;
    }

    public void setLifeform(Life lifeform) {
        this.lifeform = lifeform;
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

    public Map<String, Integer> getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Map<String, Integer> atmosphere) {
        this.atmosphere = atmosphere;
    }
}
