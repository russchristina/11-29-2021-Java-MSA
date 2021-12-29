package com.revature.model;

import java.util.Objects;

public class Vehicle {

    private int vehicle_id;
    private String vehicle_style;
    private String vehicle_maker;
    private String vehicle_info;

    public Vehicle() {
        super();
    }

    public Vehicle(int vehicle_id, String vehicle_style, String vehicle_maker, String vehicle_info) {
        super();
        this.vehicle_id = vehicle_id;
        this.vehicle_style = vehicle_style;
        this.vehicle_maker = vehicle_maker;
        this.vehicle_info = vehicle_info;
    }

    public int getVehicle_id() {
        return this.vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_style() {
        return this.vehicle_style;
    }

    public void setVehicle_style(String vehicle_style) {
        this.vehicle_style = vehicle_style;
    }

    public String getVehicle_maker() {
        return this.vehicle_maker;
    }

    public void setVehicle_maker(String vehicle_maker) {
        this.vehicle_maker = vehicle_maker;
    }

    public String getVehicle_info() {
        return this.vehicle_info;
    }

    public void setVehicle_info(String vehicle_info) {
        this.vehicle_info = vehicle_info;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return vehicle_id == vehicle.vehicle_id && Objects.equals(vehicle_style, vehicle.vehicle_style)
                && Objects.equals(vehicle_maker, vehicle.vehicle_maker)
                && Objects.equals(vehicle_info, vehicle.vehicle_info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle_id, vehicle_style, vehicle_maker, vehicle_info);
    }

    @Override
    public String toString() {
        return "{" +
                " vehicle_id='" + getVehicle_id() + "'" +
                ", vehicle_style='" + getVehicle_style() + "'" +
                ", vehicle_maker='" + getVehicle_maker() + "'" +
                ", vehicle_info='" + getVehicle_info() + "'" +
                "}";
    }

}
