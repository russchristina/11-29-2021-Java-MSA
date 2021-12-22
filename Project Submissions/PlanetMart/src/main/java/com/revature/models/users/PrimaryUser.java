package com.revature.models.users;

import com.revature.database.DummyCustomerData;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.*;
import com.revature.service.exceptions.EmptyInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class PrimaryUser extends User{

    protected final Logger log = LoggerFactory.getLogger(PrimaryUser.class);

    protected String username;

    public PrimaryUser(int id, String name, int balance, String username) {
        super(id, name, balance);
        this.username = username;
    }

    public User addSecondaryUser(String name, CustomerAccount account) throws EmptyInputException, RepeatedNameOfUserException, MaxSecondaryUsersException {
        if(name.trim().contentEquals("") || name.isEmpty()) throw new EmptyInputException("Empty name");

        List<User> users = account.getUsers();
        if(users.size() >= account.getMaxSecondaryAccounts()) throw new MaxSecondaryUsersException();
        for (User user : users) {
            if(user.getName().contentEquals(name)){
                throw new RepeatedNameOfUserException();
            }

        }

        User user = new User(DummyCustomerData.users1.size(), name, 0);
        account.addUser(user);
        return user;
    }

    public boolean removeSecondaryUser(String name, CustomerAccount account) throws EmptyInputException, UserNotFoundException {
        if(name.trim().contentEquals("") || name.isEmpty()) throw new EmptyInputException("Empty name");
        List<User> users = account.getUsers();

        for (User user : users) {
            if(user.getName().contentEquals(name)){
                users.remove(user);
                return true;
            }
        }
        throw new UserNotFoundException();
    }

    public int transferFundsToUser(int amount, String name, CustomerAccount account) throws FailedToTransferFundsException, UserNotFoundException {
        List<User> users = account.getUsers();

        for (User user : users) {
            if(user.getName().contentEquals(name)){
                try {
                    user.addFunds(removeFunds(amount));
                    return user.getBalance();
                } catch (NegativeAmountException e) {
                    System.out.println("\nNegative amount not allowed.\n");
                    log.debug(e.toString());
                    throw new FailedToTransferFundsException();
                } catch (InsufficientFundsException e) {
                    System.out.println("\nInsufficient Funds.\n");
                    log.debug(e.toString());
                    throw new FailedToTransferFundsException();
                }
            }
        }
        throw new UserNotFoundException();
    }

    public int transferFundsFromUserToUser(int amount, String fromUser, String toUser, CustomerAccount account) throws FailedToTransferFundsException, UserNotFoundException {
        List<User> users = account.getUsers();

        User user1 = null;
        User user2 = null;
        for (User user : users) {
            if(user.getName().contentEquals(fromUser)){
                user1 = user;
            }
            if(user.getName().contentEquals(toUser)){
                user2 = user;
            }
        }
        if(user1 == null | user2 == null) throw new UserNotFoundException();

        try {
            user2.addFunds(user1.removeFunds(amount));
            return user2.getBalance();
        } catch (NegativeAmountException | InsufficientFundsException e) {
            log.debug(e.toString());
            throw new FailedToTransferFundsException();
        }
    }

    public int transferFundsFromUserToPrimary(int amount, String name, CustomerAccount account) throws UserNotFoundException, FailedToTransferFundsException {
        List<User> users = account.getUsers();
        User userTransfering = null;
        for (User user : users) {
            if(user.getName().contentEquals(name)) userTransfering = user;
        }
        if(userTransfering == null) throw new UserNotFoundException();

        try {
            addFunds(userTransfering.removeFunds(amount));
            return getBalance();
        } catch (NegativeAmountException | InsufficientFundsException e) {
            log.debug(e.toString());
            throw new FailedToTransferFundsException();
        }
    }

    public String changeName(String name, CustomerAccount account) throws EmptyInputException, RepeatedNameOfUserException {
        List<User> users = account.getUsers();

        if(name.trim().contentEquals("") || name.isEmpty()) throw new EmptyInputException("Empty name");
        if(name.contentEquals(getName()) || users.contains(name)) throw new RepeatedNameOfUserException();
        setName(name);

        return getName();
    }

    public String changeNameOfUser(String newName, String originalName, CustomerAccount account) throws UserNotFoundException, RepeatedNameOfUserException, EmptyInputException {
        if(newName.trim().contentEquals("") || newName.isEmpty()) throw new EmptyInputException("Empty input new name");
        if(originalName.trim().contentEquals("") || originalName.isEmpty()) throw new EmptyInputException("Empty input user name");

        List<User> users = account.getUsers();
        User changingUser = null;
        for (User user : users) {
            if(user.getName().contentEquals(newName)) throw new RepeatedNameOfUserException();
            if(user.getName().contentEquals(originalName)) changingUser = user;
        }

        if(changingUser == null) throw new UserNotFoundException();
        changingUser.setName(newName);
        return changingUser.getName();
    }


}
