package Controller.util.models;

public class LoginReceived {

    private String name;
    private String pass;

    public LoginReceived() {
    }

    public LoginReceived(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginReceived that = (LoginReceived) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getPass() != null ? getPass().equals(that.getPass()) : that.getPass() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPass() != null ? getPass().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LoginReceived{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
