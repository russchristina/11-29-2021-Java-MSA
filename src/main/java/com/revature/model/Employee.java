package com.revature.model;

public class Employee {
	private int id;
    private String eUserName;
    private String ePassword;
    private boolean isAdmin;


    public Employee() {
        super();
    }

    public Employee(int id, String eUserName, String ePassword, boolean isAdmin) {
        this .id = id;
        this.eUserName = eUserName;
        this.ePassword = ePassword;
        this.isAdmin = isAdmin;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geteUserName() {
        return eUserName;
    }

    public void seteUserName(String eUserName) {
        this.eUserName = eUserName;
    }

    public String getePassword() {
        return ePassword;
    }

    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee that = (Employee) o;

        if (getId() != that.getId()) return false;
        if (isAdmin != that.isAdmin) return false;
        if (geteUserName() != null ? !geteUserName().equals(that.geteUserName()) : that.geteUserName() != null)
            return false;
        return getePassword() != null ? getePassword().equals(that.getePassword()) : that.getePassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (geteUserName() != null ? geteUserName().hashCode() : 0);
        result = 31 * result + (getePassword() != null ? getePassword().hashCode() : 0);
        result = 31 * result + (isAdmin ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + id  + ",'"
                + eUserName + '\'' + ",'"
                + ePassword + '\'' + ", $" +
                isAdmin +  "}\n";
    }
}
