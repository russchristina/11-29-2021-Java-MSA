package com.revature;

import java.util.List;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImplement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    @BeforeAll
    public void setup() {
        employeeRepository = new EmployeeRepositoryImplement();
    }

    @Test
    @DisplayName("make sure getting password back")
    public void testPassword() {
        String retrievePass = employeeRepository.retrievePassword("Tom Jones");

        Assertions.assertNull(retrievePass);

    }

    @Test
    @DisplayName("testing find all method")
    public void listEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        Assertions.assertNull(employeeList);
    }
}