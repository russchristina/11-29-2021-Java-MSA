package com.revature.database;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Planet;
import com.revature.models.shop.generator.PlanetGenerator;
import com.revature.models.users.PrimaryUser;
import com.revature.models.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyCustomerData {

    public static Map<String, String> userCredentials = new HashMap<>();

    public static List<Account> accounts = new ArrayList<>();
    public static List<User> users1 = new ArrayList<>();
    public static List<User> users2 = new ArrayList<>();
    public static List<User> users3 = new ArrayList<>();

    public static List<Planet> userOwnedPlanetsList = new ArrayList<>();

    public static PrimaryUser user1 = new PrimaryUser(1,"Joleyne", 10234500, "user1");
    public static PrimaryUser user2 = new PrimaryUser(3,"Bojo", 315442, "user2");
    public static PrimaryUser user3 = new PrimaryUser( 3,"HeyHo", 231234, "user3");

    private static final PlanetGenerator planetGenerator = new PlanetGenerator();

    static {
        userCredentials.put("user1", "pass1");
        userCredentials.put("user2", "pass2");
        userCredentials.put("user3", "pass3");

        users1.add(user1);
        users2.add(user2);
        users3.add(user3);
        users1.add(new User(4,"Joseph", 63453));
        users2.add(new User(5,"Jojo", 230345));

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

        userOwnedPlanetsList.add(planet1);
        userOwnedPlanetsList.add(planet2);
        userOwnedPlanetsList.add(planet3);
        userOwnedPlanetsList.add(planet4);

        CustomerAccount dummyAccount1 = new CustomerAccount( 1, "user1", users1, 1, userOwnedPlanetsList);
        CustomerAccount dummyAccount2 = new CustomerAccount( 2, "user2", users2, 2, userOwnedPlanetsList);
        CustomerAccount dummyAccount3 = new CustomerAccount( 3, "user3", users3, 3, userOwnedPlanetsList);

        accounts.add(dummyAccount1);
        accounts.add(dummyAccount2);
        accounts.add(dummyAccount3);

    }

    /*
   password and username check are temporarily used to work with the dummy data, will be changed when database is being implemented
     */

}
