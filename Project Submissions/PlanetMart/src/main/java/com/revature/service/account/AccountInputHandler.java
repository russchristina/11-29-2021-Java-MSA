package com.revature.service.account;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;
import com.revature.models.exceptions.UserNotFoundException;

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
