package com.revature.model;

import java.util.Objects;

public class Users {

    private int users_id;
    private String users_name;
    private String users_password;
    private String secondary_users;
    private float users_balance;
    private String users_address;
    private String users_DOB;

    public Users() {
        super();
    }

    public Users(int user_id, String user_name, String user_password, String secondary_user, float user_balance,
            String user_address, String user_DOB) {
        super();
        this.users_id = user_id;
        this.users_name = user_name;
        this.users_password = user_password;
        this.secondary_users = secondary_user;
        this.users_balance = user_balance;
        this.users_address = user_address;
        this.users_DOB = user_DOB;
    }

    public int getUsers_id() {
        return this.users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getUsers_name() {
        return this.users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getUsers_password() {
        return this.users_password;
    }

    public void setUsers_password(String user_password) {
        this.users_password = user_password;
    }

    public String getSecondary_users() {
        return this.secondary_users;
    }

    public void setSecondary_users(String secondary_users) {
        this.secondary_users = secondary_users;
    }

    public float getUsers_balance() {
        return this.users_balance;
    }

    public void setUsers_balance(float user_balance) {
        this.users_balance = user_balance;
    }

    public String getUsers_address() {
        return this.users_address;
    }

    public void setUsers_address(String users_address) {
        this.users_address = users_address;
    }

    public String getUsers_DOB() {
        return this.users_DOB;
    }

    public void setUsers_DOB(String users_DOB) {
        this.users_DOB = users_DOB;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Users)) {
            return false;
        }
        Users users = (Users) o;
        return users_id == users.users_id && Objects.equals(users_name, users.users_name)
                && Objects.equals(users_password, users.users_password)
                && Objects.equals(secondary_users, users.secondary_users) && users_balance == users.users_balance
                && Objects.equals(users_address, users.users_address) && Objects.equals(users_DOB, users.users_DOB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users_id, users_name, users_password, secondary_users, users_balance, users_address,
                users_DOB);
    }

    @Override
    public String toString() {
        return "{" +
                " user_id='" + getUsers_id() + "'" +
                ", user_name='" + getUsers_name() + "'" +
                ", user_password='" + getUsers_password() + "'" +
                ", secondary_user='" + getSecondary_users() + "'" +
                ", user_balance='" + getUsers_balance() + "'" +
                ", user_address='" + getUsers_address() + "'" +
                ", user_DOB='" + getUsers_DOB() + "'" +
                "}";
    }

}
