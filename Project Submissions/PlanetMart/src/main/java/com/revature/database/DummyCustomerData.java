package com.revature.database;

import com.revature.models.accounts.Account;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.generator.PlanetGenerator;
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

    public static PrimaryUser user1 = new PrimaryUser("Joleyne", 10234500, "user1");

    static {
        PlanetGenerator planetGenerator = new PlanetGenerator();

        usernames.add("user1");
        usernames.add("user2");
        usernames.add("user3");

        passwords.add("pass1");
        passwords.add("pass2");
        passwords.add("pass3");

        PrimaryUser user2 = new PrimaryUser("Bojo", 315442, "user2");
        PrimaryUser user3 = new PrimaryUser("HeyHo", 231234, "user3");

        User secondary1 = new User("Joseph", 63453, "user1");
        User secondary2 = new User("Jojo", 230345, "user2");




        secondaryUsers1.put("Joseph", secondary1);
        secondaryUsers2.put("Jojo", secondary2);


        Planet planet1 = planetGenerator.generateRandomPlanet();
        planet1.setOwner(user1);
        planet1.setUsername("user1");

        Planet planet2 = planetGenerator.generateRandomPlanet();
        planet2.setOwner(user2);
        planet2.setUsername("user2");

        Planet planet3 = planetGenerator.generateRandomPlanet();
        planet3.setOwner(user2);
        planet3.setUsername("user2");

        Planet planet4 = planetGenerator.generateRandomPlanet();
        planet4.setOwner(user3);
        planet4.setUsername("user3");

        Planet planet5 = planetGenerator.generateRandomPlanet();
        planet5.setOwner(secondary1);
        planet5.setUsername("secondary1");

        Planet planet6 = planetGenerator.generateRandomPlanet();
        planet6.setOwner(secondary2);
        planet6.setUsername("secondary2");

        userOwnedPlanetsList.add(planet1);
        userOwnedPlanetsList.add(planet2);
        userOwnedPlanetsList.add(planet3);
        userOwnedPlanetsList.add(planet4);
        userOwnedPlanetsList.add(planet5);
        userOwnedPlanetsList.add(planet6);

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
