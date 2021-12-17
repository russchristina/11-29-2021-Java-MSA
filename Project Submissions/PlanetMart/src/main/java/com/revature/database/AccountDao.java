package com.revature.database;

import com.revature.models.accounts.Account;
import com.revature.models.shop.Planet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.revature.database.DummyCustomerData.userOwnedPlanetsList;

public class AccountDao {

    //add, modify, read, delete

    public Account getAccount(String username){
        return DummyCustomerData.accountMap.get(username);
    }


    public List<Planet> getOwnedPlanets(String name) {
        List<Planet> planetsOwnedByAccount = new ArrayList<>();
        for(Planet p: DummyCustomerData.userOwnedPlanetsList){
            if(p.getUsername().contentEquals(name)) planetsOwnedByAccount.add(p);
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

}
