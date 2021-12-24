package com.revature.repository.DAOInterface;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;

import java.util.List;

public interface CustomerAccountDAOInterface {

    List<CustomerAccount> getAllCustomerAccounts();
    List<CustomerAccount> getCustomerAccountsByUserCredentialId(int id);
    List<CustomerAccount> getCustomerAccountsByPrimaryUserId(int id);

    void deleteCustomerAccountById(int id);
    void addCustomerAccount(int primaryUserId);

}
