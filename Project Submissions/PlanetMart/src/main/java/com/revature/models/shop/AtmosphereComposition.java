package com.revature.models.shop;

import java.util.Map;
import java.util.Objects;

public class AtmosphereComposition {

    private int atmosphere_id;
    private Map<String, Integer> atmosphereMap;
    private int planetId;

    public AtmosphereComposition(int atmosphere_id, Map<String, Integer> atmosphereMap, int planetId) {
        this.atmosphere_id = atmosphere_id;
        this.atmosphereMap = atmosphereMap;
        this.planetId = planetId;
    }

    public AtmosphereComposition() {

    }

    @Override
    public String toString() {
        return "{\"AtmosphereComposition\":{"
                + "\"atmosphere_id\":\"" + atmosphere_id + "\""
                + ", \"atmosphereMap\":" + atmosphereMap
                + ", \"planetId\":\"" + planetId + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtmosphereComposition)) return false;
        AtmosphereComposition that = (AtmosphereComposition) o;
        return getAtmosphere_id() == that.getAtmosphere_id() && getPlanetId() == that.getPlanetId() && Objects.equals(getAtmosphereMap(), that.getAtmosphereMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAtmosphere_id(), getAtmosphereMap(), getPlanetId());
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

    public int getPlanetId() {
        return planetId;
    }

    public void setPlanetId(int planetId) {
        this.planetId = planetId;
    }
}
