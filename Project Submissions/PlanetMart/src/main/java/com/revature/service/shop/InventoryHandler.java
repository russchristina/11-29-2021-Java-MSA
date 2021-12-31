package com.revature.service.shop;

import com.revature.display.account.AccountDisplay;
import com.revature.display.account.LifeDisplay;
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
    ShopHandler shopHandler = new ShopHandler();
    Shop shop = new Shop();

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
            System.out.println(createShapes.indent + "LOADING");
            System.out.println();
            temporaryPlanetList = planetToTempPlanet.getUsersTemporaryPlanets(user);
            Inventory inventory = iDao.getInventoryByInventoryId(user.getInventoryId());

            if(inventory != null){
                planetDisplay.displayInventoryOpen(temporaryPlanetList, inventory);

            }
            inventoryOptions(temporaryPlanetList, user, customerAccount, inventory);

        } catch (SQLException | InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
        } catch (NoPlanetFoundException e) {
            errorLogger.error(e.toString());
        }
    }

    private void inventoryOptions(List<TemporaryPlanet> temporaryPlanetList, User user, CustomerAccount customerAccount, Inventory inventory) {

        boolean chooseOptions = true;
        do{
            System.out.println();
            System.out.println(createShapes.border);
            System.out.println(createShapes.indent + "INVENTORY OPTIONS");
            input.setLength(0);
            System.out.println(createShapes.indent + "1. VIEW PLANET DETAILS");
            System.out.println(createShapes.indent + "2. MANAGE BALANCE");
            System.out.println(createShapes.indent + "3. COMMUNICATE WITH PLANET");
            System.out.println(createShapes.indent + "4. SELL PLANET");
            System.out.println(createShapes.indent + "5. RETURN");

            System.out.print(createShapes.indent + "-> ");
            input.append(sc.nextLine().trim());
            switch (input.toString()){
                case ("1"):
                    System.out.println(createShapes.indent + "OPTION 1: VIEW PLANET DETAILS");
                    inventoryPlanetDetailView(temporaryPlanetList, user, customerAccount, inventory);
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "OPTION 2: MANAGE BALANCE");
                    manageBalance(customerAccount, user);
                    break;
                case ("3"):
                    System.out.println(createShapes.indent + "OPTION 3: COMMUNICATE WITH PLANET");
                    LifeInputHandler lifeInputHandler = new LifeInputHandler();
                    lifeInputHandler.communicate(temporaryPlanetList, user, customerAccount);
                    break;
                case ("4"):
                    System.out.println("OPTION 4: SELL PLANET");
                    shopHandler.sellPlanet(customerAccount, user, new Shop());
                case ("5"):
                    System.out.println(createShapes.indent + "RETURNING");
                    chooseOptions = false;
                    return;
                default:
                    System.out.println(createShapes.indent + "CHOOSE A VALID OPTION");
                    break;
            }
        }while(chooseOptions);
    }

    private void inventoryPlanetDetailView(List<TemporaryPlanet> temporaryPlanetList, User user, CustomerAccount customerAccount, Inventory inventory) {
        System.out.println(createShapes.border);
        boolean inputting = true;
        boolean choosingOptions = true;

        do {
            System.out.println(createShapes.indent + "PLANET DETAILED VIEW");
            System.out.println(createShapes.indent + "INPUT VALID PLANET NAME OR N TO RETURN");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());
            if (input.toString().contentEquals("N")) return;

                for (TemporaryPlanet temporaryPlanet : temporaryPlanetList) {
                    if(input.toString().contentEquals(temporaryPlanet.getName())){
                        planetDisplay.viewPlanetDetails(temporaryPlanetList, temporaryPlanet);
                        System.out.println(createShapes.border);
                        System.out.println(createShapes.indent + "1. COMMUNICATE WITH PLANET");
                        System.out.println(createShapes.indent + "2. SELL PLANET");
                        System.out.println(createShapes.indent + "3. RETURN");

                        while(choosingOptions){
                            System.out.print(createShapes.indent + "-> ");
                            input.setLength(0);
                            input.append(sc.nextLine().trim());
                            switch (input.toString()){
                                case("1"):
                                    LifeDisplay lifeDisplay = new LifeDisplay();
                                    lifeDisplay.communicateWithLife(temporaryPlanet.getLifeform(), user);
                                    choosingOptions = false;
                                    break;
                                case("2"):
                                    shop.sellPlanet(temporaryPlanet, user, temporaryPlanetList, inventory);
                                    choosingOptions = false;
                                    break;
                                case("3"):
                                    choosingOptions = false;
                                    return;
                                default:
                                    System.out.println(createShapes.indent + "INPUT VALID OPTION");
                                    break;
                            }
                        }
                    }
                }
            } while(inputting);


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
        AccountDisplay accountDisplay = new AccountDisplay();

        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        if(users.size() < 2) {
            System.out.println(createShapes.indent + "NOT ENOUGH USERS TO TRANSFER FUNDS");
            return;
        }
        boolean chooseUser = true;
        boolean chooseUser2 = true;
        User firstUser = null;
        User secondUser = null;

        System.out.println(createShapes.border);

        for (User user1 : users) {
            System.out.println();
            System.out.println(createShapes.indent + "USER ID: " + user1.getUserId());
            System.out.println(createShapes.indent + "USER NAME: " + user1.getName());
            try {
                System.out.println(createShapes.indent + "BALANCE: " + inventoryDAO.getInventoryByInventoryId(user1.getInventoryId()).getBalance());
            } catch (InvalidInventoryIdException e) {
                debugLogger.debug(e.toString());
            }
        }

        if(!users.isEmpty()){
            do{
                System.out.println();
                System.out.println(createShapes.indent + "CHOOSE USER NUMBER OR TYPE N TO RETURN");
                System.out.print(createShapes.indent + "-> ");
                input.setLength(0);
                input.append(sc.nextLine().trim());

                if(input.toString().contentEquals("N")){
                    return;
                }
                for (User cUser : users) {
                    if (String.valueOf(cUser.getUserId()).contentEquals(input)) {
                        firstUser = cUser;
                        do{

                            users.remove(firstUser);
                            chooseUser = false;
                            for (User user2 : users) {
                                System.out.println();
                                System.out.println(createShapes.indent + "USER ID: " + user2.getUserId());
                                System.out.println(createShapes.indent + "USER NAME: " + user2.getName());
                                try {
                                    System.out.println(createShapes.indent + "BALANCE: " + inventoryDAO.getInventoryByInventoryId(user2.getInventoryId()).getBalance());
                                } catch (InvalidInventoryIdException e) {
                                    debugLogger.debug(e.toString());
                                }

                            }
                            System.out.println(createShapes.indent + "CHOOSE USER TO TRANSFER TO...");
                            System.out.print(createShapes.indent + "-> ");
                            input.setLength(0);
                            input.append(sc.nextLine().trim());

                            for (User cUser2 : users) {
                                if (String.valueOf(cUser2.getUserId()).contentEquals(input)) {
                                    secondUser = cUser2;
                                    System.out.println();
                                    System.out.println(createShapes.indent + "TRANSFERRING FROM: " + firstUser.getName());
                                    System.out.println(createShapes.indent + "TRANSFERRING TO: " + secondUser.getName());
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
        System.out.println();
        System.out.println(createShapes.indent + firstUser.getName() + " BALANCE: " + firstInventory.getBalance());
        System.out.println(createShapes.indent + secondUser.getName() + " BALANCE: " + secondInventory.getBalance());
        System.out.println();
        int amount = 0;
        boolean inputtingMoney = true;

        while(inputtingMoney){
            input.setLength(0);
            System.out.println(createShapes.indent + "HOW MUCH TO REMOVE...");
            System.out.print(createShapes.indent + "-> ");
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
        System.out.println();
        System.out.println(createShapes.indent + "USER ID: " + firstUser.getName());
        System.out.println(createShapes.indent + "NEW BALANCE: " + firstInventory.getBalance());

        secondInventory.setBalance(secondInventory.getBalance()+amount);
        try {
            inventoryDAO.updateInventoryBalance(secondInventory.getId(), secondInventory.getBalance());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
            System.out.println(createShapes.indent + "INVALID INVENTORY ID");
        }
        System.out.println();
        System.out.println(createShapes.indent + "USER ID: " + secondUser.getName());
        System.out.println(createShapes.indent + "NEW BALANCE: " + secondInventory.getBalance());

        transactionLogger.info("BALANCE TRANSFER BETWEEN USERS" + "USER 1 ID: " + firstUser.getUserId() + " AMOUNT REMOVED: " + amount +
                " USER 2 ID: " + secondUser.getUserId() + " INVENTORY 1 ID: " + firstInventory.getId() + " INVENTORY 1 BALANCE: " + firstInventory.getBalance() +
                " INVENTORY 2 ID: " + secondInventory.getId() + " INVENTORY 2 BALANCE: " + secondInventory.getBalance());

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
            planetDisplay.displayInventoryOpen(temporaryPlanetList, inventory);
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
        }



//        planetDisplay.displayTemporaryPlanetList(temporaryPlanetList);

    }
}