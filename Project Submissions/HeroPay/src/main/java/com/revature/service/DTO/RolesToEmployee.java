package com.revature.service.DTO;

import com.revature.repository.DTO.EmployeeAccountEntity;

import java.util.List;
import java.util.Objects;

public class RolesToEmployee {

    private int roleId;
    private List<EmployeeAccountEntity> employees;

    public RolesToEmployee() {
    }

    public RolesToEmployee(int roleId, List<EmployeeAccountEntity> employees) {
        this.roleId = roleId;
        this.employees = employees;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<EmployeeAccountEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeAccountEntity> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RolesToEmployee)) return false;
        RolesToEmployee that = (RolesToEmployee) o;
        return roleId == that.roleId && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, employees);
    }

    @Override
    public String toString() {
        return "{\"RolesToEmployee\":{"
                + "\"roleId\":\"" + roleId + "\""
                + ", \"employees\":" + employees
                + "}}";
    }
}
