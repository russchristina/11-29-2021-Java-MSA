package com.revature.project.util;

public class EmployeeUserSpecs {
    private int id;
    private String username;
    private String userPass;
    private boolean isAdmin;


    public EmployeeUserSpecs() {
        super();
    }

    public EmployeeUserSpecs(int id, String username, String userPass, boolean isAdmin) {
        this .id = id;
        this.username = username;
        this.userPass = userPass;
        this.isAdmin = isAdmin;
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

        EmployeeUserSpecs that = (EmployeeUserSpecs) o;

        if (getId() != that.getId()) return false;
        if (isAdmin != that.isAdmin) return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        return getUserPass() != null ? getUserPass().equals(that.getUserPass()) : that.getUserPass() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getUserPass() != null ? getUserPass().hashCode() : 0);
        result = 31 * result + (isAdmin ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + id  + ",'"
                + username + '\'' + ",'"
                + userPass + '\'' + ", $" +
                isAdmin +  "}\n";
    }
}
