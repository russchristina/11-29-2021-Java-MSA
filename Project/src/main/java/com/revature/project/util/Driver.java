package com.revature.project.util;

import java.util.Arrays;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        UserDB userDB = new UserDBImplementation();
//    System.out.println(userDB.findAll().size());
//            UserSpecs specs = new UserSpecs();
//        System.out.println(userDB.findByName("Jeff"));


//            List<String> test = userDB.findByName("Jeff");
//            if(test.contains("Epstein")) System.out.println("GOTEM");
//            else System.out.println("RIP BOZO");
//            String [] testing = {};
//            testing = test.toArray(new String[test.size()]);
//        System.out.println(Arrays.toString(testing));
//        System.out.println(testing.length);
//        System.out.println(userDB.findInfo("Jason"));
//        System.out.println(userDB.findInfo("Jason").size());
//        UserSpecs userSpecs = new UserSpecs(11, "Jason", "Epstein", 44444);
//            userDB.delete(userSpecs);
            UserSpecs userSpecs1 = new UserSpecs(0, "Blickman", "idfkbro", 50040);
            userDB.updateFunds(userSpecs1);
        System.out.println(userDB.findAll());

//        System.out.println(userDB.findById(2));
//        userDB.save(userSpecs);
//    UserSpecs toDelete = new UserSpecs(14, "null", "null");


    }
    }