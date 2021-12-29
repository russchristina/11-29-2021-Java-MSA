package com.revature.model;

import java.util.Objects;

public class Administrator {

    private int admin_id;
    private String admin_password;

    public Administrator() {
        super();
    }

    public Administrator(int admin_id, String admin_password) {
        this.admin_id = admin_id;
        this.admin_password = admin_password;
    }

    public int getAdmin_id() {
        return this.admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_password() {
        return this.admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Administrator)) {
            return false;
        }
        Administrator administrator = (Administrator) o;
        return admin_id == administrator.admin_id && Objects.equals(admin_password, administrator.admin_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admin_id, admin_password);
    }

    @Override
    public String toString() {
        return "{" +
                " admin_id='" + getAdmin_id() + "'" +
                ", admin_password='" + getAdmin_password() + "'" +
                "}";
    }

}
