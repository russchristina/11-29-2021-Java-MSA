package com.revature;

import java.util.Scanner;


public class MooVSelection {
	
	

        Scanner scanner = new Scanner(System.in);
        int userMooVChoice;
        boolean exit = false;
        {
        
       //List of MooVs streaming
        	  System.out.println(" ");
        	  System.out.println("Please pick a MooV or show from the list below: ");
              System.out.println("1. The Last Show on Broadway ");
              System.out.println("2. Baking is for Champs.. or is it? ");
              System.out.println("3. Rain blood on the city ");
              System.out.print("4. Exit");

              userMooVChoice = scanner.nextInt();
              switch (userMooVChoice) {
              
              case 1:
                   //User chooses MooV the application ends
                    System.out.print("Now Watching: ");
                    System.out.println("The Last Show on Broadway.");
                    System.out.println("Enjoy!");
                    System.exit(userMooVChoice);
                    break;

                    
                    
                    
              case 2:
            	//User chooses MooV the application ends
                    System.out.print("Now watching:  ");
                    System.out.println("Baking is for Champs.. or is it?");
                    System.out.println("Enjoy!");
                    System.exit(userMooVChoice);
                    break;

                    
                    
                    
              case 3:
            	//User chooses MooV the application ends
            	  System.out.print("Now watching:  ");
                  System.out.println("Rain blood on the city");
                  System.out.println("Enjoy!");
                  System.exit(userMooVChoice);
                    break;

                    
                    
                    
                    
              case 4:
            	  //User chooses to exit application
                    exit = true;
                    System.out.println("Thank you for watching MooV-Night Streaming Services! Good-bye!");
                    System.exit(userMooVChoice);
                    break;
              default:
                    System.out.println("Invaild");
                    break;
              }

              System.out.println();
        

  }
		public void userMooVSelection() {
			// TODO Auto-generated method stub
			
		}
	
}
