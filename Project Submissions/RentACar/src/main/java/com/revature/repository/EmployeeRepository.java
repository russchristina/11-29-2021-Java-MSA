package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeRepository {

    void save(Employee employee);

    Employee findById(int id);

    Employee findByName(String name);

    List<Employee> findAll();

    void update(Employee employee);

    void delete(Employee employee);

    public String retrieveEmployee(String employee_name);

    public String retrievePassword(String employee_user);
}
