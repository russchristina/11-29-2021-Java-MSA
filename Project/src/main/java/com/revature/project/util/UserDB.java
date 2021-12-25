package com.revature.project.util;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    List<String> findByName(String name);

    /**
     * This method locates every single user in our database
     */
    List<UserSpecs> findAll();

    /**
     *change user info
     */
    void update(UserSpecs specs);

    /**
     * delete user
     */
    UserSpecs delete(UserSpecs specs);

//    UserSpecs findPass (UserSpecs specs);
        ArrayList<String> findPass (String name);

}
