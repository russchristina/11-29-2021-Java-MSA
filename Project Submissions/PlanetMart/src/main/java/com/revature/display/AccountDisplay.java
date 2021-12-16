package com.revature.display;

import com.revature.models.CustomerAccount;
import com.revature.models.User;

public class AccountDisplay {

    public void displayAccountName(String username){
        System.out.printf("Welcome %s \n",username);
    }

    public void displayAccountBalance(int balance){
        System.out.printf("Balance: $%d \n", balance);
    }


    public void customerDisplay(CustomerAccount customerAccount) {
        System.out.printf("Welcome %s \n",customerAccount.getUsername());
        System.out.printf("Primary User:\n%s \n", customerAccount.getPrimaryUser().getName());
        System.out.println("Secondary Users: ");
        if(customerAccount.getSecondaryUsers().isEmpty())System.out.println("None...");
        customerAccount.getSecondaryUsers().forEach((name, User) -> System.out.println(name));
        System.out.printf("Enter User name to choose: ");
    }

    public void displayCurrentUser(User userChosen) {

        System.out.printf("Hello %s \n", userChosen.getName());
        System.out.printf("Balance: %d \n", userChosen.getBalance());
        System.out.printf("Inventory: \n", userChosen.getInventory().toString());
    }
}
