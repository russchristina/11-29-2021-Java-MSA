package com.revature.project.util;

//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
//       UserDB userDB = new UserDBImplementation();
//        Logger myLogger = LoggerFactory.getLogger(Driver.class);
//        myLogger.debug("It aint raaaat");
//        myLogger.error("OMG");

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
//           userDB.delete(userSpecs);
//        String test =  String.valueOf(userDBTest.findInfo(String.valueOf(userSpecs)).get(1));
//        userDBTest.findInfo(test);
//            UserSpecs userSpecs1 = new UserSpecs(0, "Blickman", "idfkbro", 50040);
//            userDB.updateFunds(userSpecs1);
////        System.out.println(userDB.findAll());
//String b = String.valueOf(userDB.findChildInfo("John").get(3));
//        System.out.println(b);


//        UserSpecs userSpecs = new UserSpecs(0,"John","null",0);
//        System.out.println(userDB.findInfo(String.valueOf(userSpecs)));
//
        UserDBImplementation userDBImplementation = new UserDBImplementation();
        // login
        //username
        //password
        UserSpecs user1 = new UserSpecs(0, "John", "pass", 0);
        UserSpecs returnedUser = userDBImplementation.returnUser(user1);

        if(returnedUser != null){
            System.out.println(returnedUser.toString());
            System.out.println(returnedUser.getUsername());
            System.out.println(returnedUser.getId());
            List<UserSpecs> userSpecsList = new ArrayList<>();
            userSpecsList.add(returnedUser);
            for (UserSpecs userSpecs : userSpecsList) {
                if(userSpecs.getUsername().equals(user1.getUsername())){
                    System.out.println("THEY ARE THE SAME USERNAME");
                }
            }
        }else{
            System.out.println("NULL");
        }



//        int a = Integer.parseInt(userDB.findInfo("iluvfries").get(0));
//        UserSpecs specs = new UserSpecs(a, "","",0);
//        userDB.delete(specs);
//        System.out.println(userDB.findAll());
//        System.out.println(userDB.findAllChildren());
//        System.out.println(userDB.findAllEmployees());
//        String a = String.valueOf(userDB.findChildInfo(""));
//ChildUserSpecs childUserSpecs = new ChildUserSpecs(0,"","","");
//UserSpecs userSpecs = new UserSpecs(0, "Rond1a", "hgchg", 0);

//int ab = Integer.parseInt(userDB.findInfo("Johnny").get(0));
//
//        System.out.println(ab);
//        String userPass = String.valueOf(userDB.findInfo("Jofbhnny").get(2));
//        System.out.println(userPass);
        //        System.out.println(userDB.findById(2));
//        userDB.save(userSpecs);
//    UserSpecs toDelete = new UserSpecs(14, "null", "null");


    }
    }
