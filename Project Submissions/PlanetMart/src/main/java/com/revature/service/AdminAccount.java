package com.revature.service;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.accounts.EmployeeAccount;
import com.revature.models.shop.Life;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;

import java.util.List;

public class AdminAccount extends EmployeeAccount {



    public List<EmployeeAccount> getAllEmployeeAccounts(){
        //DOA Interaction - read
        return null;
    }

    public List<CustomerAccount> getAllCustomerAccounts(){
        return null;
    }



    public List<User> getAllUsers(){
        //DOA Interaction - read
        return null;
    }

    public void deleteCustomerAccount(CustomerAccount account){
        //DOA Interaction - delete
    }

    public void deleteEmployeeAccount(EmployeeAccount account){

    }

    public void deleteUser(User user){
        //DOA Interaction - delete
    }

    public void updateCustomerAccount(CustomerAccount account){
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
