package com.revature.repository;

import java.util.List;

import com.revature.model.Administrator;

public interface AdministratorRepository {

    void save(Administrator administrator);

    Administrator findById(int id);

    Administrator findByName(String name);

    List<Administrator> findAll();

    void update(Administrator administrator);

    void delete(Administrator administrator);

    String retrieveAdmin(String admin_name);

    public String retrievePassword(String employee_name);

}
