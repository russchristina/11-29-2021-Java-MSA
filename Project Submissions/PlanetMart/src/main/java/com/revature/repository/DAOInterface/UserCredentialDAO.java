package com.revature.repository.DAOInterface;


import com.revature.models.users.UserCredential;

import java.util.List;

public interface UserCredentialDAO {

    public List<UserCredential> getAllUserCredentials();
    public UserCredential getUserCredentialByUsername(String username);
    public void updateUserCredentialById(int id);
    public void deleteUserCredentialById(int id);
    public void addUserCredential(UserCredential user);


}
