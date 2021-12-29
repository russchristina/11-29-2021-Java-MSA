package com.revature.model;

import java.util.Objects;

public class Employee {

    private int employee_id;
    private String employee_user;
    private String employee_password;

    public Employee() {
        super();
    }

    public Employee(int employee_id, String employee_user, String employee_password) {
        super();
        this.employee_id = employee_id;
        this.employee_user = employee_user;
        this.employee_password = employee_password;
    }

    public int getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_user() {
        return this.employee_user;
    }

    public void setEmployee_user(String employee_user) {
        this.employee_user = employee_user;
    }

    public String getEmployee_password() {
        return this.employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employees = (Employee) o;
        return employee_id == employees.employee_id && Objects.equals(employee_user, employees.employee_user)
                && Objects.equals(employee_password, employees.employee_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, employee_user, employee_password);
    }

    @Override
    public String toString() {
        return "{" +
                " employee_id='" + getEmployee_id() + "'" +
                ", employee_user='" + getEmployee_user() + "'" +
                ", employee_password='" + getEmployee_password() + "'" +
                "}";
    }

}
