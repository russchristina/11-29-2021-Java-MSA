package com.revature.service.shop;

import com.revature.display.shop.PlanetShopDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.users.User;
import com.revature.repository.Exception.InvalidInventoryIdException;
import com.revature.repository.InventoryDAO;
import com.revature.repository.Exception.NoPlanetFoundException;
import com.revature.utility.PlanetToTempPlanet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ShopHandler {

    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    protected final StringBuilder input = new StringBuilder();
    protected final Scanner sc = new Scanner(System.in);

    protected final PlanetShopDisplay planetShopDisplay = new PlanetShopDisplay();

    public void beginShopping(CustomerAccount customerAccount, User user) {
        boolean choosingOptions = true;
        Shop shop = new Shop();

        List<TemporaryPlanet> planetsForSale = shop.planetsForSale();

        do {

            planetShopDisplay.displayPlanetsForSale(planetsForSale);

            System.out.println("\n1. Buy a planet");
            System.out.println("2. Sell a planet");
            System.out.println("3. Get new Options");
            System.out.println("4. Return");

            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch (input.toString()) {
                case ("1"):
                    System.out.println("\nOption 1: Buy a Planet");
                    buyPlanet(customerAccount, user, shop, planetsForSale);
                    break;
                case ("2"):
                    System.out.println("\nOption 2: Sell a Planet");
                    sellPlanet(customerAccount, user, shop, planetsForSale);
                    break;
                case ("3"):
                    System.out.println("\nOption 3: Get new Options");
                    planetsForSale.clear();
                    planetsForSale = shop.planetsForSale();
                    break;
                case ("4"):
                    System.out.println("\nOption 3: Return");
                    choosingOptions = false;
                    break;
                default:
                    System.out.println("\nInput invalid, please try again.\n");
                    break;
            }
        } while (choosingOptions);



    }

    private void sellPlanet(CustomerAccount customerAccount, User user, Shop shop, List<TemporaryPlanet> planetsForSale) {

        InventoryDAO iDao = new InventoryDAO();
        List<TemporaryPlanet> temporaryPlanetList = null;
        PlanetToTempPlanet planetToTempPlanet = new PlanetToTempPlanet();
        try {
            temporaryPlanetList = planetToTempPlanet.getUsersTemporaryPlanets(user);
        } catch (SQLException | NoPlanetFoundException e) {
            debugLogger.debug(e.toString());
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
        System.out.println(temporaryPlanetList);

        boolean sellingPlanet = true;
        do {
            input.setLength(0);
            System.out.println("Type a valid planet name or type n to leave");
            input.append(sc.nextLine());

            for (TemporaryPlanet planet : temporaryPlanetList) {
                if(planet.getName().contentEquals(input)){
                    shop.sellPlanet(planet, user, temporaryPlanetList, planetsForSale, inventory);
                    sellingPlanet = false;
                    System.out.println("Successful Sale of Planet: " + input + "\n");
                    System.out.print("BALANCE: ");
                    System.out.println(inventory.getBalance());
                    break;
                }
            }
            if (input.toString().trim().toLowerCase().contentEquals("n")) {
                sellingPlanet = false;
            }
        } while (sellingPlanet);


    }

    private void buyPlanet(CustomerAccount customerAccount, User user, Shop shop, List<TemporaryPlanet> planetsForSale) {

        InventoryDAO iDao = new InventoryDAO();
        Inventory inventory = null;
        try {
            inventory = iDao.getInventoryByInventoryId(user.getInventoryId());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
        }
        boolean buyingPlanet = true;
        do {
            input.setLength(0);
            System.out.println("Type a valid planet name or type n to leave");
            input.append(sc.nextLine());

            for (TemporaryPlanet planet : planetsForSale) {
                if(planet.getName().contentEquals(input)){
                    try {
                        shop.buyPlanet(planet, user, planetsForSale, inventory);
                    } catch (InsufficientFundsException e) {
                        transactionLogger.info(e.toString());
                        System.out.println("\nINSUFFICIENT FUNDS\n");
                        return;
                    }
                    buyingPlanet = false;
                    System.out.println("Successful purchase of Planet: " + input + "\n");
                    System.out.print("BALANCE: ");
                    System.out.println(inventory.getBalance());
                    transactionLogger.info("Planet Purchase " + input +
                            "\nUser ID: " + user.getUserId() + "\nAccount ID: " + customerAccount.getCustomerAccountId());
                    break;
                }
            }
            if (input.toString().trim().toLowerCase().contentEquals("n")) {
                buyingPlanet = false;
            }
        } while (buyingPlanet);

    }
}

