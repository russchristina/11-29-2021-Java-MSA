package com.revature.service.shop;

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

    CreateShapes createShapes = new CreateShapes();

    public void beginShopping(CustomerAccount customerAccount, User user) {
        boolean choosingOptions = true;
        Shop shop = new Shop();

        List<TemporaryPlanet> planetsForSale = shop.planetsForSale();

        do {

//            planetShopDisplay.displayPlanetsForSale(planetsForSale);

            System.out.println(createShapes.indent + "1. BUY A PLANET");
            System.out.println(createShapes.indent + "2. SELL A PLANET");
            System.out.println(createShapes.indent + "3. REFRESH CATALOGUE");
            System.out.println(createShapes.indent + "4. RETURN");

            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch (input.toString()) {
                case ("1"):
                    System.out.println(createShapes.indent + "Option 1: BUY A PLANET");
                    buyPlanet(customerAccount, user, shop, planetsForSale);
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "OPTION 2: SELL A PLANET");
                    sellPlanet(customerAccount, user, shop, planetsForSale);
                    break;
                case ("3"):
                    System.out.println(createShapes.indent + "OPTION 3: REFRESH CATALOGUE");
                    planetsForSale.clear();
                    planetsForSale = shop.planetsForSale();
                    break;
                case ("4"):
                    System.out.println(createShapes.indent + "OPTION 3: RETURN");
                    choosingOptions = false;
                    break;
                default:
                    System.out.println(createShapes.indent + "INVALID INPUT, TRY AGAIN");
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
            System.out.println(createShapes.indent + "BALANCE:");
            System.out.println(createShapes.indent + inventory.getBalance());
        }
        System.out.println(createShapes.indent + temporaryPlanetList);

        boolean sellingPlanet = true;
        do {
            input.setLength(0);
            System.out.println(createShapes.indent + "TYPE A VALID PLANET NAME OR N TO LEAVE");
            input.append(sc.nextLine());

            for (TemporaryPlanet planet : temporaryPlanetList) {
                if(planet.getName().contentEquals(input)){
                    shop.sellPlanet(planet, user, temporaryPlanetList, planetsForSale, inventory);
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
        Inventory inventory = null;
        try {
            inventory = iDao.getInventoryByInventoryId(user.getInventoryId());
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(e.toString());
        }
        boolean buyingPlanet = true;
        do {
            planetShopDisplay.displayPlanetsForSaleSlideshow(planetsForSale);
            input.setLength(0);
            System.out.print(createShapes.indent + "INPUT NAME OF PLANET TO BUY OR N TO LEAVE: ");
            input.append(sc.nextLine());
            if (input.toString().trim().contentEquals("N")) {
                buyingPlanet = false;
                return;
            }

            for (TemporaryPlanet planet : planetsForSale) {
                if(planet.getName().contentEquals(input)){
                    try {
                        shop.buyPlanet(planet, user, planetsForSale, inventory);
                    } catch (InsufficientFundsException e) {
                        transactionLogger.info(e.toString());
                        System.out.println(createShapes.indent + "INSUFFICIENT FUNDS");
                        return;
                    }
                    buyingPlanet = false;
                    System.out.println(createShapes.indent + "SUCCESSFUL PURCHASE OF PLANET: " + input + "");
                    System.out.print(createShapes.indent + "BALANCE: ");
                    System.out.println(createShapes.indent + inventory.getBalance());
                    transactionLogger.info("Planet Purchase " + input +
                            "User ID: " + user.getUserId() + "Account ID: " + customerAccount.getCustomerAccountId());
                    break;
                }
            }

        } while (buyingPlanet);

    }
}

