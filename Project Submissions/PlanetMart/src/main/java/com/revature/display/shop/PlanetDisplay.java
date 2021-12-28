package com.revature.display.shop;

import com.revature.display.utility.CreateShapes;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.service.shop.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PlanetDisplay {
    protected final StringBuilder input = new StringBuilder();
    protected final Scanner sc = new Scanner(System.in);
    CreateShapes createShapes = new CreateShapes();
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");


    public void displayTemporaryPlanetList(List<TemporaryPlanet> temporaryPlanetList) {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "VIEWING OWNED PLANET INVENTORY");
        if(temporaryPlanetList.isEmpty()) {
            System.out.println(createShapes.indent + "NO PLANETS IN INVENTORY");
            return;
        }
        boolean checkingPlanets = true;
        boolean getInput;
        int planetCatalogueNumber = 0;
        int planetCatalogueMax = temporaryPlanetList.size() - 1;
        PlanetDisplay planetDisplay = new PlanetDisplay();
        while (checkingPlanets) {
            getInput = true;
            System.out.println(createShapes.border);
            System.out.println(createShapes.indent + "PLANET: " + temporaryPlanetList.get(planetCatalogueNumber).getName());
            System.out.println(createShapes.indent + "GOLDILOCK ZONE? " + temporaryPlanetList.get(planetCatalogueNumber).isGoldilocksZone());
            System.out.println(createShapes.indent + "WATER: " + temporaryPlanetList.get(planetCatalogueNumber).getWaterPercent() + "%");
            System.out.println(createShapes.indent + "AVERAGE SURFACE TEMPERATURE: " + temporaryPlanetList.get(planetCatalogueNumber).getAverageTemperature() +" K : " + (temporaryPlanetList.get(planetCatalogueNumber).getAverageTemperature()-273) + " C");
            System.out.println(createShapes.indent + "PLANET ATMOSPHERE COMPOSITION: ");
            temporaryPlanetList.get(planetCatalogueNumber).getAtmosphere().forEach((gas, amount) -> {
                if (amount > 0) System.out.println(createShapes.indent + gas + " - " + amount + "%");
            });

            if (temporaryPlanetList.get(planetCatalogueNumber).getLifeform() != null) {
                System.out.println();
                System.out.println(createShapes.indent + "LIFE-FORM");
                System.out.println(createShapes.indent + "NAME: " + temporaryPlanetList.get(planetCatalogueNumber).getLifeform().getName());
                System.out.println(createShapes.indent + "POPULATION: " + temporaryPlanetList.get(planetCatalogueNumber).getLifeform().getPopulation());
                System.out.println(createShapes.indent + "TECHNOLOGY LEVEL: " + temporaryPlanetList.get(planetCatalogueNumber).getLifeform().getTechnologyLevel());
            }

            displayPlanetArt(planetCatalogueNumber);

            do {
                System.out.println(createShapes.border);
                System.out.println(createShapes.indent + "MOVE THROUGH PLANET INVENTORY");
                System.out.println(createShapes.indent + "1. NEXT");
                System.out.println(createShapes.indent + "2. PREVIOUS");
                System.out.println(createShapes.indent + "3. RETURN");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                switch (input.toString()) {
                    case ("1"):
                        if (planetCatalogueNumber == planetCatalogueMax) {
                            System.out.println(createShapes.indent + "AT LAST PLANET");
                            break;
                        }
                        planetCatalogueNumber++;
                        getInput = false;
                        break;
                    case ("2"):
                        if (planetCatalogueNumber == 0) {
                            System.out.println(createShapes.indent + "AT FIRST PLANET");
                            break;
                        }
                        planetCatalogueNumber--;
                        getInput = false;
                        break;
                    case ("3"):
                        System.out.println(createShapes.indent + "RETURNING");
                        return;
                    default:
                        System.out.println(createShapes.indent + "CHOOSE A VALID INPUT");
                        break;
                }
            } while (getInput);

        }
    }

    public void displayPlanetArt(int planetCatalogueNumber) {

        File fileArt = new File("src/main/resources/Planet"+planetCatalogueNumber+".txt");
        FileReader fileReaderArt = null;
        BufferedReader bufferedReader = null;
        try {
            fileReaderArt = new FileReader(fileArt);
            bufferedReader = new BufferedReader(fileReaderArt);
            while(bufferedReader.ready()){
                String line = bufferedReader.readLine();
                System.out.println(line);
                Thread.sleep(60);
            }

        } catch (InterruptedException | IOException e) {
            errorLogger.error(String.valueOf(e));
        } finally{
            try {
                fileReaderArt.close();
                bufferedReader.close();
            } catch (IOException e) {
                errorLogger.error(String.valueOf(e));
            }
        }

    }
}
