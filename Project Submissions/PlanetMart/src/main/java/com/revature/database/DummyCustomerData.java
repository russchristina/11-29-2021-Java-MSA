package com.revature.database;

import com.revature.models.accounts.Account;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.PrimaryUser;
import com.revature.models.users.User;

import java.util.*;

public class DummyCustomerData {

    public static Set<String> usernames = new HashSet<>();
    public static ArrayList<String> passwords = new ArrayList<>();

    public static Map<String, Account> accountMap = new HashMap<>();

    public static Map<String, User> secondaryUsers1 = new HashMap<>();
    public static Map<String, User> secondaryUsers2 = new HashMap<>();
    public static Map<String, User> secondaryUsers3 = new HashMap<>();

    public static List<Planet> userOwnedPlanetsList = new ArrayList<>();

    public static PrimaryUser user1 = new PrimaryUser("Joleyne", 1000, "user1");

    static {
        usernames.add("user1");
        usernames.add("user2");
        usernames.add("user3");

        passwords.add("pass1");
        passwords.add("pass2");
        passwords.add("pass3");

        PrimaryUser user2 = new PrimaryUser("Bojo", 3142, "user2");
        PrimaryUser user3 = new PrimaryUser("HeyHo", 23124, "user3");

        User secondary1 = new User("Joseph", 10, "user1");
        User secondary2 = new User("Jojo", 230, "user2");




        secondaryUsers1.put("Joseph", secondary1);
        secondaryUsers2.put("Jojo", secondary2);



        userOwnedPlanetsList.add(new Planet(10, "myOwnPlanet", user1, "user1"));
        userOwnedPlanetsList.add(new Planet(102, "PlanetPOlanet", user1, "user1"));
        userOwnedPlanetsList.add(new Planet(1042, "metyyas THe 1st", user2, "user2"));
        userOwnedPlanetsList.add(new Planet(1230, "Little big planet", user3, "user3"));
        userOwnedPlanetsList.add(new Planet(102, "not my problem", secondary1, "secondary1"));
        userOwnedPlanetsList.add(new Planet(13240, "merry planet 23st", secondary2, "secondary2"));

        CustomerAccount dummyAccount1 = new CustomerAccount(secondaryUsers1, "user1", user1, userOwnedPlanetsList);
        CustomerAccount dummyAccount2 = new CustomerAccount(secondaryUsers2, "user2", user2, userOwnedPlanetsList);
        CustomerAccount dummyAccount3 = new CustomerAccount(secondaryUsers3, "user3", user3, userOwnedPlanetsList);

        accountMap.put("user1", dummyAccount1);
        accountMap.put("user2", dummyAccount2);
        accountMap.put("user3", dummyAccount3);

    }

    /*
   password and username check are temporarily used to work with the dummy data, will be changed when database is being implemented
     */

}
