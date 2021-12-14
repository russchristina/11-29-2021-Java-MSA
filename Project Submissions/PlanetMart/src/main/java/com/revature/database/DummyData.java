package com.revature.database;

import com.revature.database.exceptions.FailedToCreateAccountException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DummyData {

    public static Set<String> usernames = new HashSet<>();
    public static ArrayList<String> passwords = new ArrayList<>();

    static {
        usernames.add("user1");
        usernames.add("user2");
        passwords.add("pass1");
        passwords.add("pass2");
    }

    public static boolean userCredentialCheck(String username, String password) throws IncorrectAccountCredentialsException{
        if(usernameCheck(username)) return passwordCheck(password);
        return false;
    }

    /*
   password and username check are temporarily used to work with the dummy data, will be changed when database is being implemented
     */
    private static boolean passwordCheck(String password) {
        boolean success = false;
        for(String s : passwords) if(s.contentEquals(password)) success = true;
        return success;
    }

    public static boolean usernameCheck(String username){
        boolean success = false;
        for(String s : usernames) if(s.contentEquals(username)) success = true;
        return success;
    }

    public static void usernameDuplicateCheck(String newUser, String password) throws FailedToCreateAccountException {
        if(usernames.contains(newUser)) {
            throw new FailedToCreateAccountException("Duplicate username");
        }
        addNewAccount(newUser, password);
    }

    public static void addNewAccount(String newUser, String password) {
        usernames.add(newUser);
        passwords.add(password);
    }
}
