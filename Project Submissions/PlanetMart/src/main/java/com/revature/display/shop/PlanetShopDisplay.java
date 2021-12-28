package com.revature.display.shop;

import com.revature.display.utility.CreateShapes;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.service.shop.Shop;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PlanetShopDisplay {

    protected final StringBuilder input = new StringBuilder();
    protected final Scanner sc = new Scanner(System.in);
    Shop shop = new Shop();
    CreateShapes createShapes = new CreateShapes();

//    public void displayPlanetsForSaleSlideshow(List<TemporaryPlanet> planetsForSale) {
//        boolean checkingPlanets = true;
//        boolean getInput;
//        int planetCatalogueNumber = 0;
//        int planetCatalogueMax = planetsForSale.size() - 1;
//        PlanetDisplay planetDisplay = new PlanetDisplay();
//        System.out.println(createShapes.border);
//        System.out.println(createShapes.indent + "VIEWING PLANET CATALOGUE");
//
//        while(checkingPlanets){
//            getInput = true;
//            System.out.println(createShapes.border);
//            System.out.println(createShapes.indent + "PLANET: " + planetsForSale.get(planetCatalogueNumber).getName());
//            System.out.println(createShapes.indent + "GOLDILOCK ZONE? " + planetsForSale.get(planetCatalogueNumber).isGoldilocksZone());
//            System.out.println(createShapes.indent + "WATER: " + planetsForSale.get(planetCatalogueNumber).getWaterPercent() + "%");
//            System.out.println(createShapes.indent + "AVERAGE SURFACE TEMPERATURE: " + planetsForSale.get(planetCatalogueNumber).getAverageTemperature() +" K : " + (planetsForSale.get(planetCatalogueNumber).getAverageTemperature()-273) + " C");
//            System.out.println(createShapes.indent + "PLANET ATMOSPHERE COMPOSITION: ");
//            planetsForSale.get(planetCatalogueNumber).getAtmosphere().forEach((gas, amount) -> {
//                if(amount > 0) System.out.println(createShapes.indent + gas + " - " + amount + "%");
//            });
//
//            if(planetsForSale.get(planetCatalogueNumber).getLifeform() != null){
//                System.out.println(createShapes.indent + "LIFE-FORM");
//                System.out.println(createShapes.indent + "NAME: " + planetsForSale.get(planetCatalogueNumber).getLifeform().getName());
//                System.out.println(createShapes.indent + "POPULATION: " + planetsForSale.get(planetCatalogueNumber).getLifeform().getPopulation());
//                System.out.println(createShapes.indent + "TECHNOLOGY LEVEL: " + planetsForSale.get(planetCatalogueNumber).getLifeform().getTechnologyLevel());
//            }
//
//            planetDisplay.displayPlanetArt(planetCatalogueNumber);
//
//            do{
//                System.out.println(createShapes.border);
//                System.out.println(createShapes.indent + "MOVE THROUGH CATALOGUE");
//                System.out.println(createShapes.indent + "1. NEXT");
//                System.out.println(createShapes.indent + "2. PREVIOUS");
//                System.out.println(createShapes.indent + "3. RETURN");
//                input.setLength(0);
//                input.append(sc.nextLine().trim());
//                switch(input.toString()){
//                    case ("1"):
//                        if(planetCatalogueNumber == planetCatalogueMax) {
//                            System.out.println(createShapes.indent + "AT LAST PLANET");
//                            break;
//                        }
//                        planetCatalogueNumber++;
//                        getInput = false;
//                        break;
//                    case ("2"):
//                        if(planetCatalogueNumber == 0){
//                            System.out.println(createShapes.indent + "AT FIRST PLANET");
//                            break;
//                        }
//                        planetCatalogueNumber--;
//                        getInput = false;
//                        break;
//                    case ("3"):
//                        System.out.println(createShapes.indent + "RETURNING");
//                        getInput = false;
//                        checkingPlanets = false;
//                        return;
//                    default:
//                        System.out.println(createShapes.indent + "CHOOSE A VALID INPUT");
//                        break;
//                }
//            }while (getInput);
//        }
//    }

    public void displayPlanetsForSale(List<TemporaryPlanet> planetsForSale) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "PLANET CATALOGUE");
        PlanetDisplay planetDisplay = new PlanetDisplay();
        Iterator<TemporaryPlanet> planetIterator = planetsForSale.iterator();
        while(planetIterator.hasNext()){
            TemporaryPlanet planet = planetIterator.next();
            System.out.println(createShapes.indent + "NAME - " + planet.getName());
            System.out.println(createShapes.indent + "VALUE - " + shop.calculateValueOfPlanet(planet));
            if(planet.getLifeform() != null) System.out.println(createShapes.indent + "CIVILIZATION - " + planet.getLifeform().getName());
            System.out.println();
        }
    }
}
