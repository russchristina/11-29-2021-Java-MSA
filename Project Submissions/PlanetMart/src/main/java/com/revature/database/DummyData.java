package com.revature.database;

import java.util.*;

public class DummyData {

    public static Set<String> usernames = new HashSet<>();
    public static ArrayList<String> passwords = new ArrayList<>();

    public static Map<String, Character> accountsTypeMap = new HashMap<>();

    static {
        usernames.add("user1");
        usernames.add("user2");
        usernames.add("user3");

        passwords.add("pass1");
        passwords.add("pass2");
        passwords.add("pass3");


        accountsTypeMap.put("user1", 'c');
        accountsTypeMap.put("user2", 'e');
        accountsTypeMap.put("user3", 'a');
    }

    /*
   password and username check are temporarily used to work with the dummy data, will be changed when database is being implemented
     */

}
