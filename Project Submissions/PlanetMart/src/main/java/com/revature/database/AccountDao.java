package com.revature.database;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Planet;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.database.DummyCustomerData.userOwnedPlanetsList;

public class AccountDao {

    //Create Read Update Delete

    public Account getAccount(String username) throws AccountNotFoundException {
        for (Account account : DummyCustomerData.accounts) {
            if(account.getUsername().contentEquals(username))return account;
        }
        throw new AccountNotFoundException();
    }

    public List<Planet> getOwnedPlanets(CustomerAccount account) {
        List<Planet> planetsOwnedByAccount = new ArrayList<>();
        for(Planet p: DummyCustomerData.userOwnedPlanetsList){
            if(p.getUsername().contentEquals(account.getUsername())) planetsOwnedByAccount.add(p);
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
        userOwnedPlanetsList.remove(planet);
    }
}
