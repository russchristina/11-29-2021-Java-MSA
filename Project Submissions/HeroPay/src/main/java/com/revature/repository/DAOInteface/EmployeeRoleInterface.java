package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;

import java.util.List;


public interface EmployeeRoleInterface {

    List<EmployeeRoleEntity> getAllEmployeeRoles();

    EmployeeRoleEntity getEmployeeRoleById(EmployeeRoleEntity employeeRole);

}
