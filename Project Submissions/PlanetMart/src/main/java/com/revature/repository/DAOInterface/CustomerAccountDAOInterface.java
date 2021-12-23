package com.revature.repository.DAOInterface;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;

import java.util.List;

public interface CustomerAccountDAOInterface {

    public List<CustomerAccount> getAllCustomerAccounts();
    public List<CustomerAccount> getCustomerAccountsByPrimaryUserId(int id);
    public void updateCustomerAccountById(int id);
    public void deleteCustomerAccountById(int id);
    public void addCustomerAccount(int primaryUserId);

}
