package com.revature.database.DAO;

import com.revature.models.accounts.CustomerAccount;

import java.util.List;

public interface CustomerAccountDAO {

    public List<CustomerAccount> getAllCustomerAccounts();
    public List<CustomerAccount> getCustomerAccountsByLocation(String location);
    public void updateCustomerAccountById(int id);
    public void deleteCustomerAccountById(int id);
    public void addCustomerAccount(CustomerAccount customerAccount);

}
