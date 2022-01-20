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

@Table(name = "manager_login")
public class Manager_login {

    @Id
    @Column
    @GeneratedValue(generator = "manager_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "manager_id_seq", sequenceName = "manager_id_seq")
    private int manager_login_id;
    @Column
    private String manager_login_name;
    @Column
    private String manager_login_password;

    public Manager_login() {
        super();
    }

    public Manager_login(int manager_login_id, String manager_login_name, String manager_login_password) {
        super();
        this.manager_login_id = manager_login_id;
        this.manager_login_name = manager_login_name;
        this.manager_login_password = manager_login_password;
    }

    public int getManager_login_id() {
        return this.manager_login_id;
    }

    public void setManager_login_id(int manager_login_id) {
        this.manager_login_id = manager_login_id;
    }

    public String getManager_login_name() {
        return this.manager_login_name;
    }

    public void setManager_login_name(String manager_login_name) {
        this.manager_login_name = manager_login_name;
    }

    public String getManager_login_password() {
        return this.manager_login_password;
    }

    public void setManager_login_password(String manager_login_password) {
        this.manager_login_password = manager_login_password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Manager_login)) {
            return false;
        }
        Manager_login manager = (Manager_login) o;
        return manager_login_id == manager.manager_login_id
                && Objects.equals(manager_login_name, manager.manager_login_name)
                && Objects.equals(manager_login_password, manager.manager_login_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manager_login_id, manager_login_name, manager_login_password);
    }

    @Override
    public String toString() {
        return "{" +
                " manager_login_id='" + getManager_login_id() + "'" +
                ", manager_login_name='" + getManager_login_name() + "'" +
                ", manager_login_password='" + getManager_login_password() + "'" +
                "}";
    }

}
