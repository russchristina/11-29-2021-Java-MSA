package com.revature.models.shop;

import java.util.Objects;

public class Life {

    private int id;
    private String name;
    private int population;
    private int technologyLevel;
    private int planetId;

    public Life(int id, String name, int population, int technologyLevel, int planetId) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.technologyLevel = technologyLevel;
        this.planetId = planetId;
    }

    @Override
    public String toString() {
        return "{\"Life\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"population\":\"" + population + "\""
                + ", \"technologyLevel\":\"" + technologyLevel + "\""
                + ", \"planetId\":\"" + planetId + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Life)) return false;
        Life life = (Life) o;
        return getId() == life.getId() && getPopulation() == life.getPopulation() && getTechnologyLevel() == life.getTechnologyLevel() && getPlanetId() == life.getPlanetId() && Objects.equals(getName(), life.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPopulation(), getTechnologyLevel(), getPlanetId());
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public void setTechnologyLevel(int technologyLevel) {
        this.technologyLevel = technologyLevel;
    }

    public int getPlanetId() {
        return planetId;
    }

    public void setPlanetId(int planetId) {
        this.planetId = planetId;
    }
}
