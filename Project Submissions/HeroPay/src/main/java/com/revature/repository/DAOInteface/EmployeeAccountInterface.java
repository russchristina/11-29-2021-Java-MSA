package com.revature.repository.DAOInteface;

import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
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

    Integer insertEmployeeAccount(EmployeeAccountEntity employeeAccountEntity);

    //Read

    EmployeeAccountEntity getEmployeeAccount(int employeeId);

    List<EmployeeAccountEntity> getAllEmployeeAccountList();

    List<EmployeeAccountEntity> getEmployeeAccountsByRoleId(EmployeeAccountEntity employeeAccountEntity);


    //Update

    Integer updateEmployeeRole(EmployeeAccountEntity employeeAccountEntity);

    //Delete

    void deleteEmployeeAccount(EmployeeAccountEntity employeeAccountEntity);


}
