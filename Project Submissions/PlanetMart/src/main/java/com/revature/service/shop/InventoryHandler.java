package com.revature.service.shop;

import com.revature.display.shop.PlanetDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.users.User;
import com.revature.repository.CustomerUserDAO;
import com.revature.repository.InventoryDAO;
import com.revature.utility.PlanetToTempPlanet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class InventoryHandler {

    private final Logger log = LoggerFactory.getLogger(InventoryHandler.class);
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
                    log.debug(e.toString());
                    System.out.println("INVALID INPUT");
                    amount = 0;
                }
        }
        inventory.setBalance(inventory.getBalance()+amount);
        inventoryDAO.updateInventoryBalance(inventory.getId(), inventory.getBalance());
        System.out.println("\nNEW BALANCE...");
        System.out.println(inventory.getBalance());

    }

    public void openInventory(User user) {
        InventoryDAO iDao = new InventoryDAO();
        List<TemporaryPlanet> temporaryPlanetList = PlanetToTempPlanet.getUsersTemporaryPlanets(user);

        Inventory inventory = iDao.getInventoryByInventoryId(user.getInventoryId());

        if(inventory != null){
            System.out.println("\nBALANCE:");
            System.out.println(inventory.getBalance());
        }
        PlanetDisplay.displayTemporaryPlanetList(temporaryPlanetList);
    }

    public void manageBalance(CustomerAccount customerAccount, User user) {
        InventoryDAO inventoryDAO = new InventoryDAO();
        Inventory inventory = inventoryDAO.getInventoryByInventoryId(user.getInventoryId());
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
                log.debug(e.toString());
                System.out.println("INVALID INPUT");
                amount = 0;
            }
        }
        inventory.setBalance(inventory.getBalance()-amount);
        inventoryDAO.updateInventoryBalance(inventory.getId(), inventory.getBalance());
        System.out.println("\nNEW BALANCE...");
        System.out.println(inventory.getBalance());
    }

    public void transferFunds(CustomerAccount customerAccount, User user) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();

        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        boolean chooseUser = true;
        boolean chooseUser2 = true;
        User firstUser = null;
        User secondUser = null;

        System.out.println("USERS:");
        for (User user1 : users) {
            System.out.println(user1.getUserId());
            System.out.println(user1.getName());
            System.out.println(inventoryDAO.getInventoryByInventoryId(user1.getInventoryId()).getBalance());
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
                                System.out.println(inventoryDAO.getInventoryByInventoryId(user2.getInventoryId()).getBalance());
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
        Inventory firstInventory = inventoryDAO.getInventoryByInventoryId(firstUser.getInventoryId());
        Inventory secondInventory = inventoryDAO.getInventoryByInventoryId(secondUser.getInventoryId());

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
                log.debug(e.toString());
                System.out.println("INVALID INPUT");
                amount = 0;
            }
        }
        firstInventory.setBalance(firstInventory.getBalance()-amount);
        inventoryDAO.updateInventoryBalance(firstInventory.getId(), firstInventory.getBalance());
        System.out.println("\nNEW BALANCE FOR...");
        System.out.println(firstUser.getName());
        System.out.println(firstInventory.getBalance());

        secondInventory.setBalance(secondInventory.getBalance()+amount);
        inventoryDAO.updateInventoryBalance(secondInventory.getId(), secondInventory.getBalance());
        System.out.println("\nNEW BALANCE FOR...");
        System.out.println(secondUser.getName());
        System.out.println(secondInventory.getBalance());

    }
    }
