package com.revature.repository.DAOInterface;


import com.revature.models.users.UserCredential;
import com.revature.service.exceptions.EmptyInputException;

import java.sql.SQLException;
import java.util.List;

public interface UserCredentialDAO {

    public List<UserCredential> getAllUserCredentials();
    public UserCredential getUserCredentialByUsername(String username) throws EmptyInputException;
    public void deleteUserCredentialByUserCredentialId(int id);
    public void addUserCredential(UserCredential user) throws EmptyInputException;


    boolean readUserCredentials(int id) throws SQLException;
}
