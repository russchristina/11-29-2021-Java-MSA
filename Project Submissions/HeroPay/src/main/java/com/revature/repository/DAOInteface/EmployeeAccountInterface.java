package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface EmployeeAccountInterface {

    /*
    Create
    Read
    Update
    Delete
     */

    //Create

    EmployeeAccountEntity insertEmployeeAccount(String firstName, String lastName, int roleId) throws SQLException;

    EmployeeRoleEntity insertEmployeeRole(String roleName) throws SQLException;

    //Read

    EmployeeAccountEntity getEmployeeAccount(int employeeId) throws SQLException;

    List<EmployeeAccountEntity> getAllEmployeeAccountList() throws SQLException;

    List<EmployeeAccountEntity> getEmployeeAccountsByRoleList(int roleId) throws SQLException;

    EmployeeRoleEntity getEmployeeRoleById(int id) throws SQLException;

    EmployeeRoleEntity getEmployeeRoleByName(String roleName) throws SQLException;

    Map<Integer, String> getEmployeeRoleMap() throws SQLException;

    //Update

    EmployeeAccountEntity updateEmployeeRole(int employeeId, int roleId) throws SQLException;

    EmployeeRoleEntity updateRole(int roleId, String roleName) throws SQLException;

    //Delete



}
