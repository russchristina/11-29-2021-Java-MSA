package com.revature;

import com.revature.model.Employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeTest {

    private Employee employee;

    @BeforeAll
    public void setup() {
        employee = new Employee();
    }

    @Test
    public void employee() {
        employee.getEmployee_id();

    }

    @Test
    public void empPassword() {
        String pass = employee.getEmployee_password();

        Assertions.assertNull(pass);

    }

}