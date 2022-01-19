package daolayer;

import javax.persistence.*;

@Entity
@Table(schema = "project_one", name = "hibernate_userspecs")
public class UserSpecs {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "auto_increment", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1, name = "users_user_id_seq", sequenceName = "users_user_id_seq")
    private int userID;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String userLogin;
    @Column
    private String userPass;
    @Column
    private boolean isManager;

    public UserSpecs() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public boolean getManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }


    public UserSpecs
            (int userID, String firstName, String lastName, String userLogin, String userPass, boolean isManager) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userLogin = userLogin;
        this.userPass = userPass;
        this.isManager = isManager;
        //-------------------------------------------- BELOW THIS LINE LIES 3 HOURS OF PAIN AND SUFFERING PLEASE DONT DO THIS AGAIN
//        userID = this.userID;
//        firstName = this.firstName;
//        lastName = this.lastName;
//        userLogin = this.userLogin;
//        userPass = this.userPass;
//        isManager = this.isManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSpecs userSpecs = (UserSpecs) o;

        if (getUserID() != userSpecs.getUserID()) return false;
        if (getManager() != userSpecs.getManager()) return false;
        if (getFirstName() != null ? !getFirstName().equals(userSpecs.getFirstName()) : userSpecs.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(userSpecs.getLastName()) : userSpecs.getLastName() != null)
            return false;
        if (getUserLogin() != null ? !getUserLogin().equals(userSpecs.getUserLogin()) : userSpecs.getUserLogin() != null)
            return false;
        return getUserPass() != null ? getUserPass().equals(userSpecs.getUserPass()) : userSpecs.getUserPass() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserID();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getUserLogin() != null ? getUserLogin().hashCode() : 0);
        result = 31 * result + (getUserPass() != null ? getUserPass().hashCode() : 0);
        result = 31 * result + (getManager() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserSpecs{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userPass='" + userPass + '\'' +
                ", isManager=" + isManager +
                '}';
    }
}
