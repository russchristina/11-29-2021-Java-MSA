package com.revature.project.util;

public class ChildUserSpecs {
    private int id;
    private String username;
    private String userPass;
    private String childAdmin;


    public ChildUserSpecs() {
        super();
    }

    public ChildUserSpecs(int id, String username, String userPass,String childAdmin) {
        this .id = id;
        this.username = username;
        this.userPass = userPass;
        this.childAdmin = childAdmin;
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

        ChildUserSpecs that = (ChildUserSpecs) o;

        if (getId() != that.getId()) return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getUserPass() != null ? !getUserPass().equals(that.getUserPass()) : that.getUserPass() != null)
            return false;
        return childAdmin != null ? childAdmin.equals(that.childAdmin) : that.childAdmin == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getUserPass() != null ? getUserPass().hashCode() : 0);
        result = 31 * result + (childAdmin != null ? childAdmin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + id  + ",'"
                + username + '\'' + ",'"
                + userPass + '\'' + ", $" +
                childAdmin +   "}\n";
    }
}
