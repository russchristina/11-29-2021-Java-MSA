package com.revature.repository.DTO;

import java.util.Objects;

public class EmployeeAccountEntity {

    private int id;
    private String firstName;
    private String lastName;
    private int roleId;

    public EmployeeAccountEntity(int id, String firstName, String lastName, int roleId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
    }

    public EmployeeAccountEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeAccountEntity)) return false;
        EmployeeAccountEntity that = (EmployeeAccountEntity) o;
        return id == that.id && roleId == that.roleId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, roleId);
    }

    @Override
    public String toString() {
        return "{\"EmployeeAccountEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"roleId\":\"" + roleId + "\""
                + "}}";
    }
}
