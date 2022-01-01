package com.revature.repository.DTO;

import java.util.Objects;

public class LoginInfoEntity {

    private int id;
    private String username;
    private String password;
    private int employeeId;


    public LoginInfoEntity(int id, String username, String password, int employeeId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
    }

    public LoginInfoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginInfoEntity)) return false;
        LoginInfoEntity that = (LoginInfoEntity) o;
        return id == that.id && employeeId == that.employeeId && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, employeeId);
    }

    @Override
    public String toString() {
        return "{\"LoginInfoEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"username\":\"" + username + "\""
                + ", \"password\":\"" + password + "\""
                + ", \"employeeId\":\"" + employeeId + "\""
                + "}}";
    }
}
