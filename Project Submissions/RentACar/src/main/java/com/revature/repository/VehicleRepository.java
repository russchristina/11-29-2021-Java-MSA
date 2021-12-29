package com.revature.repository;

import com.revature.model.Vehicle;

public interface VehicleRepository {

    void save(Vehicle vehicle);

    Vehicle findById(int id);

    Vehicle findByName(String name);

    void findAll();

    void update(Vehicle vehicle);

    void delete(Vehicle vehicle);

}
