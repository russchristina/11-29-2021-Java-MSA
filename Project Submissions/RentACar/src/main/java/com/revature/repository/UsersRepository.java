package com.revature.repository;

import com.revature.model.Users;

public interface UsersRepository {

    // Users findById(int id);

    Users findByName(String name);

    // List<Users> findAll();

    void updateBalance(Float newBalance, String user_name);

    void deleteUsers(String user_name);

    void addUsers(Users users);

    void updateUsersAddress(String address, String user_name);

    void updateUsersDOB(String newDOB, String user_name);

    void changeOrAddSecondaryUser(String secondary, String user_name);

    String viewSecondaryUser(String user_name);

    String viewUserPersonalInfo(String user_name);

    Float viewUserAccountBalance(String user_name);

    String retrievePassword(String user_name);

    String retrieveUser(String user_name);

    String viewUserDOB(String user_name);

}
