package com.revature;

import com.revature.repository.VehicleRepository;
import com.revature.repository.VehicleRepositoryImplement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class VehicleRepositoryTest {

    private VehicleRepository vehicleRepository;

    @BeforeAll
    public void setup() {
        vehicleRepository = new VehicleRepositoryImplement();
    }

    @Test
    @DisplayName("make sure method works")
    public void testUser() {

        vehicleRepository.findAll();
    }

}