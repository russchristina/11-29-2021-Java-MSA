package com.revature.repository.DTO;

import java.util.Objects;

public class EmployeeRoleEntity {

    private int id;
    private String roleName;

    public EmployeeRoleEntity(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public EmployeeRoleEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeRoleEntity)) return false;
        EmployeeRoleEntity that = (EmployeeRoleEntity) o;
        return id == that.id && Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "{\"EmployeeRoleEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"roleName\":\"" + roleName + "\""
                + "}}";
    }
}
