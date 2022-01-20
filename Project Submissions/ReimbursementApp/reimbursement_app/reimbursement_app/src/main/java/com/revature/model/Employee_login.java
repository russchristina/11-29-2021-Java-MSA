package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee_login")
public class Employee_login {

    @Id
    @Column
    @GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "employee_id_seq", sequenceName = "employee_id_seq")
    private int employee_login_id;
    @Column
    private String employee_login_name;
    @Column
    private String employee_login_password;

    public Employee_login() {
        super();
    }

    public Employee_login(int employee_login_id, String employee_login_name, String employee_login_password) {
        super();
        this.employee_login_id = employee_login_id;
        this.employee_login_name = employee_login_name;
        this.employee_login_password = employee_login_password;
    }

    public int getEmployee_login_id() {
        return this.employee_login_id;
    }

    public void setEmployee_login_id(int employee_login_id) {
        this.employee_login_id = employee_login_id;
    }

    public String getEmployee_login_name() {
        return this.employee_login_name;
    }

    public void setEmployee_login_name(String employee_login_name) {
        this.employee_login_name = employee_login_name;
    }

    public String getEmployee_login_password() {
        return this.employee_login_password;
    }

    public void setEmployee_login_password(String employee_login_password) {
        this.employee_login_password = employee_login_password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee_login)) {
            return false;
        }
        Employee_login employee = (Employee_login) o;
        return employee_login_id == employee.employee_login_id
                && Objects.equals(employee_login_name, employee.employee_login_name)
                && Objects.equals(employee_login_password, employee.employee_login_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_login_id, employee_login_name, employee_login_password);
    }

    @Override
    public String toString() {
        return "{" +
                " employee_login_id='" + getEmployee_login_id() + "'" +
                ", employee_login_name='" + getEmployee_login_name() + "'" +
                ", employee_login_password='" + getEmployee_login_password() + "'" +
                "}";
    }

}
