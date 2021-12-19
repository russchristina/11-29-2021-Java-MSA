package com.revature.models.shop;

import com.revature.models.shop.generator.MarkovChain;

import java.util.Map;

public class Life {

    protected int technologyLevel;
    protected long population;
    protected String name;

    public Life(String name, long population, int technologyLevel) {
        this.name = name;
        this.population = population;
        this.technologyLevel = technologyLevel;
    }

    public StringBuilder getCommunication(){
        MarkovChain markovChain = new MarkovChain();
        return markovChain.generateParagraph();
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
}
