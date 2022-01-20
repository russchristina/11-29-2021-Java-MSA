package com.revature.repository;

import java.util.List;

import com.revature.model.Employee_login;

public interface EmployeeRepository {

  List<Employee_login> findAllEmployeeLogin();
}
