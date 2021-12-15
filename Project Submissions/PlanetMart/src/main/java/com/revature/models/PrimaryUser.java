package com.revature.models;

import com.revature.models.exceptions.MaxSecondaryUsersException;
import com.revature.models.exceptions.RepeatedNameOfUserException;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.exceptions.EmptyInputException;

import java.util.List;
import java.util.Map;

public class PrimaryUser extends User{

    private String username;

    public PrimaryUser(String name, int balance, String username) {
        super(name, balance);
        this.username = username;
    }

    public PrimaryUser() {
        super();
    }


    public User addSecondaryUser(String name, CustomerAccount account) throws EmptyInputException, RepeatedNameOfUserException, MaxSecondaryUsersException {
        if(name.trim().contentEquals("") || name.isEmpty()) throw new EmptyInputException("Empty name");

        Map<String, User> secondaryUsers = account.getSecondaryUsers();
        if(secondaryUsers.size() >= account.getMaxSecondaryAccounts()) throw new MaxSecondaryUsersException();

        if(secondaryUsers.containsKey(name)) throw new RepeatedNameOfUserException();
        User user = new User(name, 0);
        secondaryUsers.put(name, user);
        account.setSecondaryUsers(secondaryUsers);
        return user;
    }

    public boolean removeSecondaryUser(String name, CustomerAccount account) throws EmptyInputException, UserNotFoundException {
        if(name.trim().contentEquals("") || name.isEmpty()) throw new EmptyInputException("Empty name");
        Map<String, User> secondaryUsers = account.getSecondaryUsers();
        if(!secondaryUsers.containsKey(name)) throw new UserNotFoundException();
        if(secondaryUsers.remove(name) != null) return true;
        return false;
    }

    public void transferFundsToUser(int amount, User user){

    }

    public void transferFundsFromUser(int amount, User user){

    }

}
