package com.revature.models.shop;

import java.io.Serializable;
import java.util.Objects;

public class Life implements Serializable {

    private int id;
    private String name;
    private long population;
    private int technologyLevel;

    public Life(int id, String name, long population, int technologyLevel) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.technologyLevel = technologyLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public void setTechnologyLevel(int technologyLevel) {
        this.technologyLevel = technologyLevel;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Life)) return false;
        Life life = (Life) o;
        return getId() == life.getId() && getPopulation() == life.getPopulation() && getTechnologyLevel() == life.getTechnologyLevel() && Objects.equals(getName(), life.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPopulation(), getTechnologyLevel());
    }

    @Override
    public String toString() {
        return "{\"Life\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"population\":\"" + population + "\""
                + ", \"technologyLevel\":\"" + technologyLevel + "\""
                + "}}";
    }
}
