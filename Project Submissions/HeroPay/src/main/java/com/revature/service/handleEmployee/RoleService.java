package com.revature.service.handleEmployee;

import com.revature.repository.DAOClasses.EmployeeRoleDao;
import com.revature.repository.DTO.EmployeeRoleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoleService {


    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private final EmployeeRoleDao employeeRoleDao;

    public RoleService(EmployeeRoleDao employeeRoleDao) {
        this.employeeRoleDao = employeeRoleDao;
    }

    public EmployeeRoleEntity getEmployeeRole(int employeeRoleId) {
        dLog.debug("Getting employee role entity by ID: " + employeeRoleId);
        return employeeRoleDao.getEmployeeRoleById(new EmployeeRoleEntity(employeeRoleId, ""));
    }

    public List<EmployeeRoleEntity> getAllEmployeeRoles() {
        dLog.debug("Getting all employee roles");
        return employeeRoleDao.getAllEmployeeRoles();
    }

}
