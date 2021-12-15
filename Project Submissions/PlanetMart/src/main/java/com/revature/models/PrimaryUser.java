package com.revature.models;

import com.revature.models.exceptions.*;
import com.revature.service.exceptions.EmptyInputException;

import java.util.Map;

public class PrimaryUser extends User{

    private String username;

    public PrimaryUser(String name, int balance, String username) {
        super(name, balance);
        setUsername(username);
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

        return secondaryUsers.remove(name) != null;
    }

    public int transferFundsToUser(int amount, String user, CustomerAccount account) throws FailedToTransferFundsException, UserNotFoundException {
        Map<String, User> secondaryUsers = account.getSecondaryUsers();
        if(!secondaryUsers.containsKey(user)) throw new UserNotFoundException();
        try {
            User otherUser = secondaryUsers.get(user);
            otherUser.addFunds(removeFunds(amount));
            return otherUser.getBalance();
        } catch (NegativeAmountException | InsufficientFundsException e) {
            e.printStackTrace();
            throw new FailedToTransferFundsException();
        }
    }

    public int transferFundsFromUserToUser(int amount, String fromUser, String toUser, CustomerAccount account) throws FailedToTransferFundsException, UserNotFoundException {
        Map<String, User> secondaryUsers = account.getSecondaryUsers();
        if(!secondaryUsers.containsKey(fromUser) || !secondaryUsers.containsKey(toUser)) throw new UserNotFoundException();

        try {
            User user1 = secondaryUsers.get(fromUser);
            User user2 = secondaryUsers.get(toUser);

            user2.addFunds(user1.removeFunds(amount));
            return user2.getBalance();
        } catch (NegativeAmountException | InsufficientFundsException e) {
            e.printStackTrace();
            throw new FailedToTransferFundsException();
        }
    }

    public int transferFundsFromUserToPrimary(int amount, String user, CustomerAccount account) throws UserNotFoundException, FailedToTransferFundsException {
        Map<String, User> secondaryUsers = account.getSecondaryUsers();
        if(!secondaryUsers.containsKey(user)) throw new UserNotFoundException();
        try {
            User otherUser = secondaryUsers.get(user);
            addFunds(otherUser.removeFunds(amount));
            return getBalance();
        } catch (NegativeAmountException | InsufficientFundsException e) {
            e.printStackTrace();
            throw new FailedToTransferFundsException();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
