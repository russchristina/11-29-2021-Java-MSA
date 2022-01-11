package com.revature.repository.DTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee_account", schema = "project_1")
public class EmployeeAccountEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "employee_account_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "employee_account_seq", sequenceName = "employee_account_id_seq")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "role")
    private EmployeeRoleEntity employeeRole;

    public EmployeeAccountEntity(int id, String firstName, String lastName, EmployeeRoleEntity employeeRole) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeRole = employeeRole;
    }

    public EmployeeAccountEntity() {
    }

    @Override
    public String toString() {
        return "{\"EmployeeAccountEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"employeeRole\":" + employeeRole
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeAccountEntity)) return false;
        EmployeeAccountEntity that = (EmployeeAccountEntity) o;
        return getId() == that.getId() && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmployeeRole(), that.getEmployeeRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmployeeRole());
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

    public EmployeeRoleEntity getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRoleEntity employeeRole) {
        this.employeeRole = employeeRole;
    }
}
