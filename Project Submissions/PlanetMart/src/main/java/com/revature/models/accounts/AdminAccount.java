package com.revature.models.accounts;

import com.revature.models.shop.Life;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;

import java.util.List;

public class AdminAccount extends EmployeeAccount{

    public List<Account> getAllAccounts(){
        //DOA Interaction - read
        return null;
    }

    public List<User> getAllUsers(){
        //DOA Interaction - read
        return null;
    }

    public void deleteAccount(Account account){
        //DOA Interaction - delete
    }

    public void deleteUser(User user){
        //DOA Interaction - delete
    }

    public void updateAccount(Account account){
        //DOA Interaction - update
    }

    public void updateUser(User user){
        //DOA Interaction - update
    }

    public Planet createPlanet(){
        //DOA Interaction - Create
        return null;
    }

    public void UpdatePlanet(Planet planet){
        //DOA Interaction - update
    }

    public Life createLife(Planet planet){
        //DOA Interaction - Create
        return null;
    }

    public void updateLife(Life life){
        //DOA Interaction - Update
    }







}
