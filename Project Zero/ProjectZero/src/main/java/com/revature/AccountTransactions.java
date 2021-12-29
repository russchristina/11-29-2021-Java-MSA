package com.revature;

import java.util.Scanner;

public class AccountTransactions {



 void showTransactionMenu() {
	
	 		  System.out.println(" ");
      		  System.out.println("Choose one from the following list");
              System.out.println("1. Add Funds ");
              System.out.println("2. Withdraw Funds ");
              System.out.println("3. Check balance ");
	 		  System.out.println("4. Watch MooV ");
	 		  System.out.println("5. Exit ");

 }
 
 void showTransactions() {
	 
	 Scanner scanner = new Scanner(System.in);
     int userChoice;
     boolean exit = false;
     float balance = 0f;
	 
     
     do {
              userChoice = scanner.nextInt();
              switch (userChoice) {
             
              
            //If user chooses 1 then they will be adding funds to account
              case 1:
                    float amount;
                    System.out.print("Enter amount to added: ");
                    amount = scanner.nextFloat();
                    if (amount <= 0)
                         System.out.println("Invaild. Try again!");
                    else {
                         balance += amount;
                         System.out.println("$" + amount + " has been deposited.");
                    }
                    break;

                    
                    
             //If user chooses 2 then they will be withdrawing from their account. Only can withdraw up to the balance 
                    //of account
              case 2:
                    System.out.print("Enter amount to be withdrawn: ");
                    amount = scanner.nextFloat();
                    if (amount <= 0 || amount > balance)
                         System.out.println("Invaild. Try again.");
                    else {
                         balance -= amount;
                         System.out.println("$" + amount + " has been withdrawn.");
                    }
                    break;

                    
                    
               //User can check funds in account     
              case 3:
                    System.out.println("Your balance: $" + balance);
                    break;
                    //User chooses to go to the MooV selection page
              case 4: 
            	  
            	  MooVSelection MooVSelectionObj = new MooVSelection();
            		MooVSelectionObj.userMooVSelection();
            	  
            	  break; 
            	  //Exit application
              case 5:
            	  System.out.println("Thank you for using MooV-Night Streaming Services! Good-bye!");
            	  exit = true;
                  System.exit(userChoice);
                  break;

            	//User enters a number outside 1-5  
              default:
                    System.out.println("Invaild. Try again");
                    break;
              }
     
              System.out.println();
 }while (!exit);
        System.out.println("Good-bye. See you soon");}

  }