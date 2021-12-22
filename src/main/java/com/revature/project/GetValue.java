package com.revature.project;

import java.util.Scanner;

public class GetValue {
    int attempts = 3;
    GetKey key = new GetKey();

    UserAccounts allAccounts = new UserAccounts();


    public static String getAccountPassword() {
        return accountPassword;
    }

    private static String accountPassword = "null";

    public void passwordCheck() {
        Scanner passwordStream = new Scanner(System.in);
        if (key.hasKey(MainDisplay.getUsername(), allAccounts)) {
            getValue(MainDisplay.getUsername());
            System.out.println("Enter Password: ");
            String holdPassword = passwordStream.nextLine();
            MainDisplay.setPassword(holdPassword);
            if (MainDisplay.getPassword().equals(GetValue.getAccountPassword())) {
                System.out.println("Welcome, " + MainDisplay.getUsername() + "! Type in number corresponding to the desired" +
                        " action: ");
            } else {
                while (!MainDisplay.getPassword().equals(GetValue.getAccountPassword())) {
                        String breakNum = "3";
                    attempts--;
                    if (attempts == 0 ) {
                        System.out.println("Too many attempts foo :P");
                        break;

                    }
//
                  if(passwordStream.nextLine().equals("3"))
                       System.out.println("closing app");
               else
                    System.out.println("Password incorrect. Attempts " +
                            "remaining :  " + attempts);
                 breakNum = passwordStream.nextLine();

                }

                passwordStream.nextLine();

            }

        }
    }
        protected String getValue (String a){
            if (UserAccounts.getAccountHash().containsKey(a)) {
                GetValue.accountPassword = UserAccounts.getAccountHash().get(a);
            }
            return accountPassword;

        }


    }


