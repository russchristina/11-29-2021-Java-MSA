package com.revature.project;

import com.revature.project.util.*;
import com.revature.project.util.UserDB;
import com.revature.project.util.UserDBImplementation;

import java.util.Scanner;

public class GetKey {
        UserDB userDB = new UserDBImplementation();
//        GetValue getValue = new GetValue();
        public void findUsername(GetValue value) {
            if (!userDB.findInfo(MainDisplay.getUsername()).isEmpty()) {
               value.passwordCheck();
            }else if(!userDB.findChildInfo(MainDisplay.getUsername()).isEmpty()) {
                value.childPasswordCheck();
            }else if (!userDB.findEmployeeInfo(MainDisplay.getUsername()).isEmpty()){
                value.employeePasswordCheck();
            }
           else {
                System.out.println("Username not recognized");
                new MainDisplay();
            }

        }


    public boolean hasKey(String display, UserAccounts allAccounts  ){
        //Tried to make username case insensitive below :(
        //      String ignoreUsernameCase = MainDisplay.getUsername();
//        if (MainDisplay.getUsername().equalsIgnoreCase(ignoreUsernameCase)){
//        }
        display = MainDisplay.getUsername();

        return UserAccounts.getAccountHash().containsKey(MainDisplay.getUsername());


    }

}
