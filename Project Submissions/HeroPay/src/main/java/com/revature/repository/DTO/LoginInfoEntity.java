package com.revature.repository.DTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login_info", schema = "project_1")
public class LoginInfoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="login_info_id_seq", strategy=GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "login_info_id_seq", sequenceName = "login_info_id_seq")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne
    @JoinColumn(name = "id")
    private EmployeeAccountEntity employeeAccount;

    public LoginInfoEntity(int id, String username, String password, EmployeeAccountEntity employeeAccount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employeeAccount = employeeAccount;
    }

    public LoginInfoEntity() {
    }

    @Override
    public String toString() {
        return "{\"LoginInfoEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"username\":\"" + username + "\""
                + ", \"password\":\"" + password + "\""
                + ", \"employeeAccount\":" + employeeAccount
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginInfoEntity)) return false;
        LoginInfoEntity that = (LoginInfoEntity) o;
        return getId() == that.getId() && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getEmployeeAccount(), that.getEmployeeAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEmployeeAccount());
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

    public EmployeeAccountEntity getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(EmployeeAccountEntity employeeAccount) {
        this.employeeAccount = employeeAccount;
    }
}
