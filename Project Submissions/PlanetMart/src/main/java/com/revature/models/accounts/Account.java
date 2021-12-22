package com.revature.models.accounts;

import java.util.Objects;

public abstract class Account {

    protected int id;
    protected String username;
    public Account(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public Account() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getUsername().equals(account.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return "{\"Account\":{"
                + "\"username\":\"" + username + "\""
                + "}}";
    }
}
