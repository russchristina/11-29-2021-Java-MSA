package com.revature.service.shop;

import com.revature.display.account.LifeDisplay;
import com.revature.display.shop.PlanetDisplay;
import com.revature.display.shop.PlanetShopDisplay;
import com.revature.display.utility.CreateShapes;
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
    PlanetDisplay planetDisplay = new PlanetDisplay();

    CreateShapes createShapes = new CreateShapes();
    Shop shop = new Shop();

    public void beginShopping(CustomerAccount customerAccount, User user) {
        boolean choosingOptions = true;


        List<TemporaryPlanet> planetsForSale = shop.planetsForSale();

        do {

            planetShopDisplay.displayPlanetsForSale(planetsForSale);

            System.out.println(createShapes.indent + "1. VIEW PLANET IN DETAIL");
            System.out.println(createShapes.indent + "2. BUY A PLANET");
            System.out.println(createShapes.indent + "3. REFRESH CATALOGUE");
            System.out.println(createShapes.indent + "4. RETURN");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch (input.toString()) {
                case ("1"):
                    System.out.println(createShapes.indent + "OPTION 1: VIEW PLANET IN DETAIL");
                    viewPlanetDetail(planetsForSale, user);
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "OPTION 2: BUY A PLANET");
                    buyPlanet(customerAccount, user, shop, planetsForSale);
                    break;
                case ("3"):
                    System.out.println(createShapes.indent + "OPTION 3: REFRESH CATALOGUE");
                    planetsForSale.clear();
                    planetsForSale = shop.planetsForSale();
                    break;
                case ("4"):
                    System.out.println(createShapes.indent + "OPTION 3: RETURN");
                    choosingOptions = false;
                    return;
                default:
                    System.out.println(createShapes.indent + "INVALID INPUT, TRY AGAIN");
                    break;
            }
        } while (choosingOptions);

    }

    private void viewPlanetDetail(List<TemporaryPlanet> planetsForSale, User user) {
        System.out.println(createShapes.border);
        boolean inputting = true;
        boolean gettingPlanet = true;
        InventoryDAO iDao = new InventoryDAO();

        do {
            System.out.println(createShapes.indent + "INPUT VALID PLANET NAME OR N TO RETURN");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());
            if (input.toString().contentEquals("N")) return;

                for (TemporaryPlanet temporaryPlanet : planetsForSale) {
                    if(input.toString().contentEquals(temporaryPlanet.getName())){
                        planetDisplay.viewPlanetDetails(planetsForSale, temporaryPlanet);
                        System.out.println(createShapes.border);
                        System.out.println(createShapes.indent + "1. BUY PLANET");
                        System.out.println(createShapes.indent + "2. RETURN");

                        System.out.print(createShapes.indent + "-> ");
                        input.setLength(0);
                        input.append(sc.nextLine().trim());
                        switch (input.toString()){
                            case("1"):
                                try {
                                    Inventory inventory = iDao.getInventoryByInventoryId(user.getInventoryId());
                                    shop.buyPlanet(temporaryPlanet, user, planetsForSale, inventory);
                                } catch (InvalidInventoryIdException e) {
                                    debugLogger.debug(e.toString());
                                } catch (InsufficientFundsException e) {
                                    debugLogger.debug(String.valueOf(e));
                                    System.out.println(createShapes.indent + "INSUFFICIENT FUNDS");
                                }
                                gettingPlanet = false;
                                break;
                            case("2"):
                                gettingPlanet = false;
                                return;
                            default:
                                System.out.println(createShapes.indent + "INPUT VALID OPTION");
                                break;
                        }
                        break;
                    }
                }

        }while(gettingPlanet);
    }

    public void sellPlanet(CustomerAccount customerAccount, User user, Shop shop) {

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
            planetDisplay.displayInventoryOpen(temporaryPlanetList, inventory);
        }

        boolean sellingPlanet = true;
        do {
            input.setLength(0);
            System.out.println(createShapes.indent + "TYPE A VALID PLANET NAME OR N TO LEAVE");
            System.out.println(createShapes.indent + "-> ");
            input.append(sc.nextLine());

            for (TemporaryPlanet planet : temporaryPlanetList) {
                if(planet.getName().contentEquals(input)){
                    shop.sellPlanet(planet, user, temporaryPlanetList, inventory);
                    sellingPlanet = false;
                    System.out.println(createShapes.indent + "SUCCESSFUL SALE OF PLANET: " + input + "");
                    System.out.print(createShapes.indent + "BALANCE: ");
                    System.out.println(createShapes.indent + inventory.getBalance());
                    break;
                }
            }
            if (input.toString().trim().contentEquals("N")) {
                sellingPlanet = false;
            }
        } while (sellingPlanet);


    }

    private void buyPlanet(CustomerAccount customerAccount, User user, Shop shop, List<TemporaryPlanet> planetsForSale) {

        InventoryDAO iDao = new InventoryDAO();
        boolean buyingPlanet = true;
        try {
            Inventory inventory = iDao.getInventoryByInventoryId(user.getInventoryId());
            do {
                input.setLength(0);
                System.out.println(createShapes.indent + "INPUT NAME OF PLANET TO BUY OR N TO LEAVE: ");
                System.out.print(createShapes.indent + "-> ");
                input.append(sc.nextLine());
                if (input.toString().trim().contentEquals("N")) {
                    buyingPlanet = false;
                    return;
                }

                for (TemporaryPlanet planet : planetsForSale) {
                    if(planet.getName().contentEquals(input)){
                        try {
                            shop.buyPlanet(planet, user, planetsForSale, inventory);
                            System.out.println(createShapes.indent + "SUCCESSFUL PURCHASE OF PLANET: " + input + "");
                            transactionLogger.info("Planet Purchase " + input +
                                    "User ID: " + user.getUserId() + "Account ID: " + customerAccount.getCustomerAccountId());
                        } catch (InsufficientFundsException e) {
                            transactionLogger.info(e.toString());
                            System.out.println(createShapes.indent + "INSUFFICIENT FUNDS");
                            return;
                        }
                        buyingPlanet = false;

                        break;
                    }
                }

            } while (buyingPlanet);} catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
        }



    }
}

