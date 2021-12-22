package com.revature.models.accounts;

import com.revature.models.users.User;

import java.util.List;

public class EmployeeAccount {

    public List<CustomerAccount> getCustomerAccounts(){
        //DOA Interaction = Read
        return null;
    }

    public void deleteCustomerAccount(CustomerAccount account){
        //DOA Interaction - Delete
    }

    public List<User> getUsersFromAccount(CustomerAccount account){
        //DOA Interaction = Read
        return null;
    }


}
