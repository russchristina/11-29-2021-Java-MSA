package com.revature.models.shop;

import com.revature.models.shop.generator.MarkovChain;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Life implements Serializable {

    protected int id;
    private int technologyLevel;
    private long population;
    private String name;

    public Life(String name, long population, int technologyLevel) {
        this.name = name;
        this.population = population;
        this.technologyLevel = technologyLevel;
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
        return getTechnologyLevel() == life.getTechnologyLevel() && getPopulation() == life.getPopulation() && getName().equals(life.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTechnologyLevel(), getPopulation(), getName());
    }

    @Override
    public String toString() {
        return "{\"Life\":{"
                + "\"technologyLevel\":\"" + technologyLevel + "\""
                + ", \"population\":\"" + population + "\""
                + ", \"name\":\"" + name + "\""
                + "}}";
    }
}
