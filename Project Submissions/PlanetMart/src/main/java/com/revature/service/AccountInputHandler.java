package com.revature.service;

import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.models.CustomerAccount;
import com.revature.models.User;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.exceptions.EmptyInputException;

import java.util.Scanner;

public class AccountInputHandler {

    public User inputChooseUser(CustomerAccount customerAccount) throws UserNotFoundException {
        Scanner sc = new Scanner(System.in);


        String userInput = sc.nextLine();
        if(customerAccount.getPrimaryUser().getName().contentEquals(userInput)) return customerAccount.getPrimaryUser();
        else if(customerAccount.getSecondaryUsers().get(userInput) != null) return customerAccount.getSecondaryUsers().get(userInput);

        throw new UserNotFoundException();


    }
}
