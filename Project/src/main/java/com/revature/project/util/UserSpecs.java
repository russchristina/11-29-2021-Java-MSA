package com.revature.project.util;

public class UserSpecs {
    private int id;
    private String username;
    private String userPass;

    public UserSpecs() {
        super();
    }

    public UserSpecs(int id, String username, String userPass) {
        this .id = id;
        this.username = username;
        this.userPass = userPass;
    }

    public UserSpecs(String username, String userPass) {
        this.username = username;
        this.userPass = userPass;
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

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSpecs userSpecs = (UserSpecs) o;

        if (getId() != userSpecs.getId()) return false;
        if (getUsername() != null ? !getUsername().equals(userSpecs.getUsername()) : userSpecs.getUsername() != null)
            return false;
        return getUserPass() != null ? getUserPass().equals(userSpecs.getUserPass()) : userSpecs.getUserPass() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getUserPass() != null ? getUserPass().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserSpecs{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
