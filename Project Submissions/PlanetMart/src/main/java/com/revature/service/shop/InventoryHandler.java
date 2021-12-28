package com.revature.service.shop;

import com.revature.display.shop.PlanetDisplay;
import com.revature.display.utility.CreateShapes;
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
    PlanetDisplay planetDisplay = new PlanetDisplay();

    CreateShapes createShapes = new CreateShapes();


    public void addToBalance(Inventory inventory, User user, InventoryDAO inventoryDAO) {
        System.out.println(createShapes.indent + "BALANCE...");
        System.out.println(createShapes.indent + inventory.getBalance());
        int amount = 0;
        boolean inputtingMoney = true;

        while(inputtingMoney){
                input.setLength(0);
                System.out.println(createShapes.indent + "HOW MUCH...");
                input.append(sc.nextLine().trim());
                try{
                    amount = Integer.parseInt(input.toString());
                    if(amount >= 0) inputtingMoney = false;
                } catch (NumberFormatException e) {
                    debugLogger.debug(e.toString());
                    System.out.println(createShapes.indent + "INVALID INPUT");
                    amount = 0;
                }
        }
        inventory.setBalance(inventory.getBalance()+amount);
        try {
            inventoryDAO.updateInventoryBalance(inventory.getId(), inventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println(createShapes.indent + "INVALID INVENTORY ID");
        }
        System.out.println(createShapes.indent + "NEW BALANCE...");
        System.out.println(createShapes.indent + inventory.getBalance());
        transactionLogger.info("BALANCE INPUT: " + amount +
                "USER ID: " + user.getUserId() + "INVENTORY ID: " + inventory.getId() +
                "INVENTORY BALANCE: " + inventory.getBalance());

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
            System.out.println(createShapes.indent + "BALANCE:");
            System.out.println(createShapes.indent + inventory.getBalance());
        }
        planetDisplay.displayTemporaryPlanetList(temporaryPlanetList);
        inventoryOptions(temporaryPlanetList, user, customerAccount);
    }

    private void inventoryOptions(List<TemporaryPlanet> temporaryPlanetList, User user, CustomerAccount customerAccount) {

        System.out.println(createShapes.indent + "OPTIONS");
        boolean chooseOptions = true;
        do{
            input.setLength(0);
            System.out.println(createShapes.indent + "1. Manage Balance");
            System.out.println(createShapes.indent + "2. Communicate With Life Form");
            System.out.println(createShapes.indent + "3. Return");
            input.append(sc.nextLine().trim());
            switch (input.toString()){
                case ("1"):
                    System.out.println(createShapes.indent + "OPTION 1: Manage Balance");
                    manageBalance(customerAccount, user);
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "OPTION 2: Communicate with Life Form");
                    LifeInputHandler lifeInputHandler = new LifeInputHandler();
                    lifeInputHandler.communicate(temporaryPlanetList, user, customerAccount);
                    break;
                case ("3"):
                    System.out.println(createShapes.indent + "RETURNING");
                    chooseOptions = false;
                    break;
                default:
                    System.out.println(createShapes.indent + "CHOOSE A VALID OPTION");
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
            System.out.println(createShapes.indent + "CHOOSE");
            System.out.println(createShapes.indent + "1. ADD MONEY");
            System.out.println(createShapes.indent + "2. REMOVE MONEY");
            System.out.println(createShapes.indent + "3. RETURN");

            System.out.println(createShapes.indent + "...");
            input.append(sc.nextLine().trim());
            switch(input.toString()){
                case("1"):
                    System.out.println(createShapes.indent + "ADDING MONEY");
                    addToBalance(inventory, user, inventoryDAO);
                    break;
                case("2"):
                    System.out.println(createShapes.indent + "REMOVING MONEY");
                    minusBalance(inventory, user, inventoryDAO);
                    break;
                case("3"):
                    System.out.println(createShapes.indent + "RETURNING");
                    choosingOptions = false;
                    break;
                default:
                    System.out.println(createShapes.indent + "INVALID");
                    break;
            }
        }while(choosingOptions);
    }

    private void minusBalance(Inventory inventory, User user, InventoryDAO inventoryDAO) {
        System.out.println(createShapes.indent + "BALANCE...");
        System.out.println(createShapes.indent + inventory.getBalance());
        int amount = 0;
        boolean inputtingMoney = true;

        while(inputtingMoney){
            input.setLength(0);
            System.out.println(createShapes.indent + "HOW MUCH TO REMOVE...");
            input.append(sc.nextLine().trim());
            try{
                amount = Integer.parseInt(input.toString());
                if(amount >= 0 && inventory.getBalance() >= amount) inputtingMoney = false;

            } catch (NumberFormatException e) {
                debugLogger.debug(e.toString());
                System.out.println(createShapes.indent + "INVALID INPUT");
                amount = 0;
            }
        }
        inventory.setBalance(inventory.getBalance()-amount);
        try {
            inventoryDAO.updateInventoryBalance(inventory.getId(), inventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println(createShapes.indent + "INVALID INVENTORY ID");
        }
        System.out.println(createShapes.indent + "NEW BALANCE...");
        System.out.println(createShapes.indent + inventory.getBalance());
        transactionLogger.info("BALANCE INPUT: " + amount +
                "USER ID: " + user.getUserId() + "INVENTORY ID: " + inventory.getId() +
                "INVENTORY BALANCE: " + inventory.getBalance());
    }

    public void transferFunds(CustomerAccount customerAccount, User user) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();

        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        if(users.size() < 2) {
            System.out.println(createShapes.indent + "NOT ENOUGH USERS TO TRANSFER FUNDS");
            return;
        }
        boolean chooseUser = true;
        boolean chooseUser2 = true;
        User firstUser = null;
        User secondUser = null;

        System.out.println(createShapes.indent + "USERS:");
        for (User user1 : users) {
            System.out.println(createShapes.indent + user1.getUserId());
            System.out.println(createShapes.indent + user1.getName());
            try {
                System.out.println(createShapes.indent + inventoryDAO.getInventoryByInventoryId(user1.getInventoryId()).getBalance());
            } catch (InvalidInventoryIdException e) {
                debugLogger.debug(e.toString());
            }
        }
        System.out.println(createShapes.indent + users);
        if(!users.isEmpty()){

            do{
                System.out.println(createShapes.indent + "CHOOSE USER NUMBER");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                for (User cUser : users) {

                    if (String.valueOf(cUser.getUserId()).contentEquals(input)) {
                        firstUser = cUser;
                        do{
                            System.out.println(createShapes.indent + "CHOOSE USER TO TRANSFER TO...");
                            users.remove(firstUser);
                            chooseUser = false;
                            for (User user2 : users) {
                                System.out.println(createShapes.indent + user2.getUserId());
                                System.out.println(createShapes.indent + user2.getName());
                                try {
                                    System.out.println(createShapes.indent + inventoryDAO.getInventoryByInventoryId(user2.getInventoryId()).getBalance());
                                } catch (InvalidInventoryIdException e) {
                                    debugLogger.debug(e.toString());
                                    System.out.println(createShapes.indent + "INVALID INVENTORY ID");
                                }
                            }
                            input.setLength(0);
                            input.append(sc.nextLine().trim());

                            for (User cUser2 : users) {
                                if (String.valueOf(cUser2.getUserId()).contentEquals(input)) {
                                    secondUser = cUser2;
                                    System.out.println(createShapes.indent + "TRANSFERRING FROM " + firstUser.getName() + " TO " + secondUser.getName());
                                    multipleUserTransfer(firstUser, secondUser);
                                    chooseUser2 = false;
                                    return;
                                }
                            }System.out.println(createShapes.indent + "CHOOSE VALID USER NUMBER");
                            }while(chooseUser2);
                    }


                }System.out.println(createShapes.indent + "CHOOSE VALID USER NUMBER");
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
            System.out.println(createShapes.indent + "INVALID FIRST INVENTORY");
        }
        Inventory secondInventory = null;
        try {
            secondInventory = inventoryDAO.getInventoryByInventoryId(secondUser.getInventoryId());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println(createShapes.indent + "INVALID SECOND INVENTORY");
        }

        System.out.println(createShapes.indent + firstUser.getName() + " BALANCE " + firstInventory.getBalance());
        System.out.println(createShapes.indent + secondUser.getName() + " BALANCE " + secondInventory.getBalance());

        int amount = 0;
        boolean inputtingMoney = true;

        while(inputtingMoney){
            input.setLength(0);
            System.out.println(createShapes.indent + "HOW MUCH TO REMOVE...");
            input.append(sc.nextLine().trim());
            try{
                amount = Integer.parseInt(input.toString());
                if(amount >= 0 && firstInventory.getBalance() >= amount) inputtingMoney = false;

            } catch (NumberFormatException e) {
                debugLogger.debug(e.toString());
                System.out.println(createShapes.indent + "INVALID INPUT");
                amount = 0;
            }
        }
        firstInventory.setBalance(firstInventory.getBalance()-amount);
        try {
            inventoryDAO.updateInventoryBalance(firstInventory.getId(), firstInventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println(createShapes.indent + "INVALID INVENTORY ID");
        }
        System.out.println(createShapes.indent + "NEW BALANCE FOR...");
        System.out.println(createShapes.indent + firstUser.getName());
        System.out.println(createShapes.indent + firstInventory.getBalance());

        secondInventory.setBalance(secondInventory.getBalance()+amount);
        try {
            inventoryDAO.updateInventoryBalance(secondInventory.getId(), secondInventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println(createShapes.indent + "INVALID INVENTORY ID");
        }
        System.out.println(createShapes.indent + "NEW BALANCE FOR...");
        System.out.println(createShapes.indent + secondUser.getName());
        System.out.println(createShapes.indent + secondInventory.getBalance());
        transactionLogger.info("BALANCE TRANSFER BETWEEN USERS" + "USER 1 ID: " + firstUser.getUserId() + "AMOUNT REMOVED: " + amount +
                "USER 2 ID: " + secondUser.getUserId() + "INVENTORY 1 ID: " + firstInventory.getId() + "INVENTORY 1 BALANCE: " + firstInventory.getBalance() +
                "INVENTORY 2 ID: " + secondInventory.getId() + "INVENTORY 2 BALANCE: " + secondInventory.getBalance());

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
            System.out.println(createShapes.indent + "BALANCE:");
            System.out.println(createShapes.indent + inventory.getBalance());
        }
        planetDisplay.displayTemporaryPlanetList(temporaryPlanetList);

    }
}
