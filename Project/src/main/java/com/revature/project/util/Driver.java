package com.revature.project.util;

public class Driver {
    public static void main(String[] args) {
        UserDB userDB = new UserDBImplementation();
    System.out.println(userDB.findAll().size());


//        System.out.println(userDB.findById(2));
//      UserSpecs userSpecs = new UserSpecs(0, "Jeff", "Epstein");
//        userDB.save(userSpecs);
//    UserSpecs toDelete = new UserSpecs(14, "null", "null");
//    userDB.delete(toDelete);
    }
}
