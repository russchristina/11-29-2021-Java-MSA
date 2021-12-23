package com.revature.models.shop;

import com.revature.repository.AtmosphereDAO;

import java.util.Map;

public class AtmosphereComposition {

    private int atmosphere_id;
    private Map<String, Integer> atmosphereMap;

    public AtmosphereComposition(int atmosphere_id, Map<String, Integer> atmosphereMap) {
        this.atmosphere_id = atmosphere_id;
        this.atmosphereMap = atmosphereMap;
    }

    public AtmosphereComposition() {
    }

    public int getAtmosphere_id() {
        return atmosphere_id;
    }

    public void setAtmosphere_id(int atmosphere_id) {
        this.atmosphere_id = atmosphere_id;
    }

    public Map<String, Integer> getAtmosphereMap() {
        return atmosphereMap;
    }

    public void setAtmosphereMap(Map<String, Integer> atmosphereMap) {
        this.atmosphereMap = atmosphereMap;
    }
}
