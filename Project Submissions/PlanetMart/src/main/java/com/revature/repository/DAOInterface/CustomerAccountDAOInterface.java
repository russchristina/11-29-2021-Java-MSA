package com.revature.repository.DAOInterface;

import com.revature.models.accounts.CustomerAccount;
import com.revature.repository.Exception.InvalidCustomerAccountIdException;
import com.revature.repository.Exception.InvalidPrimaryUserException;
import com.revature.repository.Exception.InvalidUserCredentialException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerAccountDAOInterface {

    List<CustomerAccount> getAllCustomerAccounts();
    List<CustomerAccount> getCustomerAccountsByUserCredentialId(int id) throws InvalidUserCredentialException, SQLException;
    List<CustomerAccount> getCustomerAccountsByPrimaryUserId(int id) throws InvalidUserCredentialException, InvalidPrimaryUserException;

    void deleteCustomerAccountById(int id) throws InvalidCustomerAccountIdException;
    void addCustomerAccount(int userCredentialId, int primaryUserId) throws InvalidPrimaryUserException, SQLException, InvalidUserCredentialException;

}
