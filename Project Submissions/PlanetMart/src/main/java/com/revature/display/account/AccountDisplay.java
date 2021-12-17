package com.revature.display.account;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;

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

    public void displayCurrentUser(CustomerAccount account, User userChosen) {

        System.out.printf("Hello %s \n", userChosen.getName());
        System.out.printf("Balance: %d \n", userChosen.getBalance());
        System.out.printf("Inventory: %s\n", userChosen.getInventory(account, userChosen).toString());
    }

    public void displayCustomerOptions(CustomerAccount customerAccount, User user) {
        if(customerAccount.getSecondaryUsers().containsKey(user.getName())){
            System.out.println("SECONDARY USER OPTIONS");
            System.out.println("1. Open Inventory");
            System.out.println("2. Open Shop");
            System.out.println("3. Change User");
            System.out.println("4. Add to Balance");
            System.out.println("5. Logout");
        }else{
            System.out.println("PRIMARY USER OPTIONS");
            System.out.println("1. Open Inventory");
            System.out.println("2. Open Shop");
            System.out.println("3. Change User");
            System.out.println("4. Add to Balance");
            System.out.println("5. Logout");
            System.out.println("6. Request Account Deletion");
            System.out.println("7. Transfer Funds");
            System.out.println("8. Change user names");
            System.out.println("9. Remove User");
        }
    }
}
