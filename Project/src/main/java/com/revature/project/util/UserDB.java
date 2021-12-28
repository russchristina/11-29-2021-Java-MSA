package com.revature.project.util;

import java.util.ArrayList;
import java.util.List;

public interface UserDB {
    /**
     *  How we will save new user name and user password
     */
    void save(UserSpecs specs);

    void saveToChild(UserSpecs specs);
    /**
     *
     * @param id the primary key that is used to locate the user
     */
    UserSpecs findById(int id);

    /**
     *
     * @param name the name of the user in the database
     */

    /**
     * This method locates every single user in our database
     */
    List<UserSpecs> findAll();
    List<ChildUserSpecs> findAllChildren();
    List<EmployeeUserSpecs> findAllEmployees();
    /**
     *change user info
     */
    void update(UserSpecs specs);

    void updateUserName(UserSpecs specs);
    void updateChildUserName(ChildUserSpecs specs);
    void updateEmployeeUserName(EmployeeUserSpecs specs);

    void updateUserPass(UserSpecs specs);
    void updateChildUserPass(ChildUserSpecs specs);
    void updateEmployeeUserPass(EmployeeUserSpecs specs);





    //delete users, child users of main users, and employees of the business
    void delete(UserSpecs specs);
    ChildUserSpecs deleteAllChildren(ChildUserSpecs specs);
    void deleteChild(ChildUserSpecs specs);
    void deleteEmployee(EmployeeUserSpecs specs);
//    UserSpecs findPass (UserSpecs specs);
    ArrayList<String> findInfo (String name);
    ArrayList<String> findChildInfo (String name);
    ArrayList<String> findEmployeeInfo(String name);

    void updateFunds(UserSpecs specs);

}
