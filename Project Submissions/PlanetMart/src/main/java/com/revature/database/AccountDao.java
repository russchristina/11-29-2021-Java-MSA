package com.revature.database;

import com.revature.models.accounts.Account;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.revature.database.DummyCustomerData.userOwnedPlanetsList;

public class AccountDao {

    //Create Read Update Delete

    public Account getAccount(String username){
        return DummyCustomerData.accountMap.get(username);
    }


    public List<Planet> getOwnedPlanets(User user) {
        List<Planet> planetsOwnedByAccount = new ArrayList<>();
        for(Planet p: DummyCustomerData.userOwnedPlanetsList){
            if(p.getUsername().contentEquals(user.getPrimaryUsername())) planetsOwnedByAccount.add(p);
        }
        return planetsOwnedByAccount;
    }

    public Planet addPlanetToUserOwnedList(Planet planet) {
        if(!userOwnedPlanetsList.contains(planet)){
            userOwnedPlanetsList.add(planet);
            return planet;
        }
        return null;
    }

    public void removePlanetFromOwnedList(Planet planet) {
        if(userOwnedPlanetsList.contains(planet)){
            userOwnedPlanetsList.remove(planet);
        }
    }
}
