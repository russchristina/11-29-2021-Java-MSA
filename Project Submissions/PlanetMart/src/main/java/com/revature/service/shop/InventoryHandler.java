package com.revature.service.shop;

import com.revature.display.shop.PlanetDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.users.User;
import com.revature.repository.CustomerUserDAO;
import com.revature.repository.Exception.InvalidInventoryIdException;
import com.revature.repository.InventoryDAO;
import com.revature.repository.Exception.NoPlanetFoundException;
import com.revature.service.account.LifeInputHandler;
import com.revature.utility.PlanetToTempPlanet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InventoryHandler {

    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    private final StringBuilder input = new StringBuilder();
    private final Scanner sc = new Scanner(System.in);


    public void addToBalance(Inventory inventory, User user, InventoryDAO inventoryDAO) {
        System.out.println("\nBALANCE...");
        System.out.println(inventory.getBalance());
        int amount = 0;
        boolean inputtingMoney = true;

        while(inputtingMoney){
                input.setLength(0);
                System.out.println("HOW MUCH...");
                input.append(sc.nextLine().trim());
                try{
                    amount = Integer.parseInt(input.toString());
                    if(amount >= 0) inputtingMoney = false;
                } catch (NumberFormatException e) {
                    debugLogger.debug(e.toString());
                    System.out.println("INVALID INPUT");
                    amount = 0;
                }
        }
        inventory.setBalance(inventory.getBalance()+amount);
        try {
            inventoryDAO.updateInventoryBalance(inventory.getId(), inventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println("\nINVALID INVENTORY ID\n");
        }
        System.out.println("\nNEW BALANCE...");
        System.out.println(inventory.getBalance());
        transactionLogger.info("BALANCE INPUT: " + amount +
                "\nUSER ID: " + user.getUserId() + "\nINVENTORY ID: " + inventory.getId() +
                "\nINVENTORY BALANCE: " + inventory.getBalance());

    }

    public void openInventory(User user, CustomerAccount customerAccount) {
        InventoryDAO iDao = new InventoryDAO();
        List<TemporaryPlanet> temporaryPlanetList = null;
        PlanetToTempPlanet planetToTempPlanet = new PlanetToTempPlanet();
        try {
            temporaryPlanetList = planetToTempPlanet.getUsersTemporaryPlanets(user);
        } catch (SQLException e) {
            debugLogger.debug(e.toString());
        } catch (NoPlanetFoundException e) {
            errorLogger.error(e.toString());
        }

        Inventory inventory = null;
        try {
            inventory = iDao.getInventoryByInventoryId(user.getInventoryId());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
        }

        if(inventory != null){
            System.out.println("\nBALANCE:");
            System.out.println(inventory.getBalance());
        }
        PlanetDisplay.displayTemporaryPlanetList(temporaryPlanetList);
        inventoryOptions(temporaryPlanetList, user, customerAccount);
    }

    private void inventoryOptions(List<TemporaryPlanet> temporaryPlanetList, User user, CustomerAccount customerAccount) {

        System.out.println("\nOPTIONS\n");
        boolean chooseOptions = true;
        do{
            input.setLength(0);
            System.out.println("1. Manage Balance");
            System.out.println("2. Communicate With Life Form");
            System.out.println("3. Return");
            input.append(sc.nextLine().trim());
            switch (input.toString()){
                case ("1"):
                    System.out.println("OPTION 1: Manage Balance");
                    manageBalance(customerAccount, user);
                    break;
                case ("2"):
                    System.out.println("OPTION 2: Communicate with Life Form");
                    LifeInputHandler lifeInputHandler = new LifeInputHandler();
                    lifeInputHandler.communicate(temporaryPlanetList, user, customerAccount);
                    break;
                case ("3"):
                    System.out.println("\nRETURNING");
                    chooseOptions = false;
                    break;
                default:
                    System.out.println("\nCHOOSE A VALID OPTION");
                    break;
            }
        }while(chooseOptions);
    }


    public void manageBalance(CustomerAccount customerAccount, User user) {
        InventoryDAO inventoryDAO = new InventoryDAO();
        Inventory inventory = null;
        try {
            inventory = inventoryDAO.getInventoryByInventoryId(user.getInventoryId());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
        }
        boolean choosingOptions = true;

        do{
            input.setLength(0);
            System.out.println("CHOOSE");
            System.out.println("1. ADD MONEY");
            System.out.println("2. REMOVE MONEY");
            System.out.println("3. RETURN");

            System.out.println("...");
            input.append(sc.nextLine().trim());
            switch(input.toString()){
                case("1"):
                    System.out.println("ADDING MONEY");
                    addToBalance(inventory, user, inventoryDAO);
                    break;
                case("2"):
                    System.out.println("REMOVING MONEY");
                    minusBalance(inventory, user, inventoryDAO);
                    break;
                case("3"):
                    System.out.println("RETURNING");
                    choosingOptions = false;
                    break;
                default:
                    System.out.println("INVALID");
                    break;
            }
        }while(choosingOptions);
    }

    private void minusBalance(Inventory inventory, User user, InventoryDAO inventoryDAO) {
        System.out.println("BALANCE...");
        System.out.println(inventory.getBalance());
        int amount = 0;
        boolean inputtingMoney = true;

        while(inputtingMoney){
            input.setLength(0);
            System.out.println("\nHOW MUCH TO REMOVE...");
            input.append(sc.nextLine().trim());
            try{
                amount = Integer.parseInt(input.toString());
                if(amount >= 0 && inventory.getBalance() >= amount) inputtingMoney = false;

            } catch (NumberFormatException e) {
                debugLogger.debug(e.toString());
                System.out.println("INVALID INPUT");
                amount = 0;
            }
        }
        inventory.setBalance(inventory.getBalance()-amount);
        try {
            inventoryDAO.updateInventoryBalance(inventory.getId(), inventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println("\nINVALID INVENTORY ID\n");
        }
        System.out.println("\nNEW BALANCE...");
        System.out.println(inventory.getBalance());
        transactionLogger.info("BALANCE INPUT: " + amount +
                "\nUSER ID: " + user.getUserId() + "\nINVENTORY ID: " + inventory.getId() +
                "\nINVENTORY BALANCE: " + inventory.getBalance());
    }

    public void transferFunds(CustomerAccount customerAccount, User user) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();

        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        if(users.size() < 2) {
            System.out.println("\nNOT ENOUGH USERS TO TRANSFER FUNDS\n");
            return;
        }
        boolean chooseUser = true;
        boolean chooseUser2 = true;
        User firstUser = null;
        User secondUser = null;

        System.out.println("USERS:");
        for (User user1 : users) {
            System.out.println(user1.getUserId());
            System.out.println(user1.getName());
            try {
                System.out.println(inventoryDAO.getInventoryByInventoryId(user1.getInventoryId()).getBalance());
            } catch (InvalidInventoryIdException e) {
                debugLogger.debug(e.toString());
            }
        }
        System.out.println(users);
        if(!users.isEmpty()){

            do{
                System.out.println("\nCHOOSE USER NUMBER");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                for (User cUser : users) {

                    if (String.valueOf(cUser.getUserId()).contentEquals(input)) {
                        firstUser = cUser;
                        do{
                            System.out.println("\nCHOOSE USER TO TRANSFER TO...\n");
                            users.remove(firstUser);
                            chooseUser = false;
                            for (User user2 : users) {
                                System.out.println(user2.getUserId());
                                System.out.println(user2.getName());
                                try {
                                    System.out.println(inventoryDAO.getInventoryByInventoryId(user2.getInventoryId()).getBalance());
                                } catch (InvalidInventoryIdException e) {
                                    debugLogger.debug(e.toString());
                                    System.out.println("\nINVALID INVENTORY ID\n");
                                }
                            }
                            input.setLength(0);
                            input.append(sc.nextLine().trim());

                            for (User cUser2 : users) {
                                if (String.valueOf(cUser2.getUserId()).contentEquals(input)) {
                                    secondUser = cUser2;
                                    System.out.println("\nTRANSFERRING FROM " + firstUser.getName() + " TO " + secondUser.getName());
                                    multipleUserTransfer(firstUser, secondUser);
                                    chooseUser2 = false;
                                    return;
                                }
                            }System.out.println("CHOOSE VALID USER NUMBER");
                            }while(chooseUser2);
                    }


                }System.out.println("CHOOSE VALID USER NUMBER");
            }while(chooseUser);
        }
    }

    private void multipleUserTransfer(User firstUser, User secondUser) {
        InventoryDAO inventoryDAO = new InventoryDAO();
        Inventory firstInventory = null;
        try {
            firstInventory = inventoryDAO.getInventoryByInventoryId(firstUser.getInventoryId());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println("\nINVALID FIRST INVENTORY\n");
        }
        Inventory secondInventory = null;
        try {
            secondInventory = inventoryDAO.getInventoryByInventoryId(secondUser.getInventoryId());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println("\nINVALID SECOND INVENTORY\n");
        }

        System.out.println(firstUser.getName() + " BALANCE " + firstInventory.getBalance());
        System.out.println(secondUser.getName() + " BALANCE " + secondInventory.getBalance());

        int amount = 0;
        boolean inputtingMoney = true;

        while(inputtingMoney){
            input.setLength(0);
            System.out.println("\nHOW MUCH TO REMOVE...");
            input.append(sc.nextLine().trim());
            try{
                amount = Integer.parseInt(input.toString());
                if(amount >= 0 && firstInventory.getBalance() >= amount) inputtingMoney = false;

            } catch (NumberFormatException e) {
                debugLogger.debug(e.toString());
                System.out.println("INVALID INPUT");
                amount = 0;
            }
        }
        firstInventory.setBalance(firstInventory.getBalance()-amount);
        try {
            inventoryDAO.updateInventoryBalance(firstInventory.getId(), firstInventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println("\nINVALID INVENTORY ID\n");
        }
        System.out.println("\nNEW BALANCE FOR...");
        System.out.println(firstUser.getName());
        System.out.println(firstInventory.getBalance());

        secondInventory.setBalance(secondInventory.getBalance()+amount);
        try {
            inventoryDAO.updateInventoryBalance(secondInventory.getId(), secondInventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println("\nINVALID INVENTORY ID\n");
        }
        System.out.println("\nNEW BALANCE FOR...");
        System.out.println(secondUser.getName());
        System.out.println(secondInventory.getBalance());
        transactionLogger.info("BALANCE TRANSFER BETWEEN USERS" + "\nUSER 1 ID: " + firstUser.getUserId() + "\nAMOUNT REMOVED: " + amount +
                "\nUSER 2 ID: " + secondUser.getUserId() + "\nINVENTORY 1 ID: " + firstInventory.getId() + "\nINVENTORY 1 BALANCE: " + firstInventory.getBalance() +
                "\nINVENTORY 2 ID: " + secondInventory.getId() + "\nINVENTORY 2 BALANCE: " + secondInventory.getBalance());

    }

    public void openUserInventory(User user) {
        InventoryDAO iDao = new InventoryDAO();
        List<TemporaryPlanet> temporaryPlanetList = null;
        PlanetToTempPlanet planetToTempPlanet = new PlanetToTempPlanet();
        try {
            temporaryPlanetList = planetToTempPlanet.getUsersTemporaryPlanets(user);
        } catch (SQLException e) {
            debugLogger.debug(e.toString());
        } catch (NoPlanetFoundException e) {
            errorLogger.error(e.toString());
        }

        Inventory inventory = null;
        try {
            inventory = iDao.getInventoryByInventoryId(user.getInventoryId());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
        }

        if(inventory != null){
            System.out.println("\nBALANCE:");
            System.out.println(inventory.getBalance());
        }
        PlanetDisplay.displayTemporaryPlanetList(temporaryPlanetList);

    }
}
