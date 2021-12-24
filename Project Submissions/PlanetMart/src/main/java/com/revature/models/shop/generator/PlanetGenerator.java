package com.revature.models.shop.generator;

import com.revature.models.shop.AtmosphereComposition;
import com.revature.models.shop.Life;
import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;

import java.util.HashMap;
import java.util.Map;

public class PlanetGenerator {

    private char[] consonants = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
    private char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    private String[] gases = {"Water", "Oxygen", "Hydrogen", "Nitrogen", "Argon", "Helium", "Carbon Dioxide", "Methane", "Chlorine"};


    public TemporaryPlanet generateRandomPlanet(){

        boolean goldiLocksZone = isGoldilocksZone();
        int waterPercent = generateWater(goldiLocksZone);
        int averageTemperature = generateAverageTemperature(goldiLocksZone, waterPercent);
        Map<String, Integer> atmosphere = generateAtmosphere(goldiLocksZone, waterPercent);

        Life lifeForm = generateLife(goldiLocksZone,waterPercent,averageTemperature);

        return new TemporaryPlanet(generateNameSimple(), lifeForm,0, goldiLocksZone, waterPercent, averageTemperature, atmosphere );
    }

    private Life generateLife(boolean goldiLocksZone, int waterPercent, int averageTemperature) {
        if(goldiLocksZone && waterPercent >= 5 && averageTemperature >= 20 && averageTemperature <= 60){
            Life life = new Life(0, generateName().toString(), (int)(Math.random()*20_000_000) + 100_000, (int)(Math.random()*4), 0);
            return life;
        }
        return null;
    }

    private Map<String,Integer> generateAtmosphere(boolean goldiLocksZone, int waterPercent) {
        Map<String, Integer> atmosphereComposition = new HashMap<>();
        atmosphereComposition.put("Water", 0);
        atmosphereComposition.put("Oxygen", 0);
        atmosphereComposition.put("Hydrogen", 0);
        atmosphereComposition.put("Nitrogen", 0);
        atmosphereComposition.put("Argon", 0);
        atmosphereComposition.put("Helium", 0);
        atmosphereComposition.put("Carbon Dioxide", 0);
        atmosphereComposition.put("Methane", 0);
        atmosphereComposition.put("Chlorine", 0);
        atmosphereComposition.put("Unknown", 0);
        int max = 100;
        int gasesAvailable = (int)(Math.random()*3)+2;
        int input;
        int gasAvailable = 0;
        String gas;
        boolean generatingGases = true;
        boolean getReasonableNumber = true;
        if(goldiLocksZone || waterPercent >= 30){
            int waterAmount = (int)(Math.random()*30 + 10);
            max -= waterAmount;
            atmosphereComposition.replace("Water", 0, waterAmount);
        }


        for(int i = 0; i < gasesAvailable; i++){
            int loopCounter = 0;
            if(!generatingGases) generatingGases = true;
            while(generatingGases){
                input = (int)(Math.random()*8);
                gas = gases[input];
                if(!atmosphereComposition.containsKey(gases)){

                    if(!getReasonableNumber) getReasonableNumber = true;

                    if(i == gasAvailable -1){
                        atmosphereComposition.replace(gas, 0, max);
                        getReasonableNumber = false;
                    }
                    while(getReasonableNumber){
                        loopCounter++;
                        gasAvailable = max - ((int)(Math.random()*60 + i));
                        if(gasAvailable >= 10 & gasAvailable <= 60){
                            atmosphereComposition.replace(gas,0, gasAvailable);
                            max -= gasAvailable;
                            generatingGases = false;
                            getReasonableNumber = false;
                        }
                        if(loopCounter >= 25){
                            atmosphereComposition.replace(gas,0,max);
                            getReasonableNumber = false;
                            generatingGases = false;
                            break;
                        }
                    }
                }
            }
        }
        if(max> 0) {
            int maximum = 100;
            int runningTotal = 0;
            for (Map.Entry<String, Integer> entry : atmosphereComposition.entrySet()) {
                Integer integer = entry.getValue();
                runningTotal += integer;
            }
            if(maximum-runningTotal != 0) atmosphereComposition.replace("Unknown",0,maximum-runningTotal );
        }
        return atmosphereComposition;
    }

    private String generateNameSimple() {
        StringBuilder gasName = new StringBuilder();
        for(int i = 0; i < (int)(Math.random()*3)+1;i++){
            gasName.append(String.valueOf(consonants[(int) (Math.random() * 20)]).toUpperCase());
        }
        gasName.append('-');
        return gasName.append((int) (Math.random() * 100 + 20)).toString();
    }

    private int generateAverageTemperature(boolean goldiLocksZone, int waterPercent) {
        if(goldiLocksZone) return (int)(Math.random()*50)+273;
        if(waterPercent >= 10) return ((int)(Math.random()*300)+100);
        return ((int)(Math.random()*972)+1);
    }

    private int generateWater(boolean goldiLocksZone) {
        if(goldiLocksZone) return (int)(Math.random()*80)+10;

        return (int)(Math.random()*60)+1;
    }

    private boolean isGoldilocksZone() {
        int testingNumber = 0;
        testingNumber = (int)(Math.random()*20)+1;
        if(testingNumber >= 5) return false;

        return true;
    }

    private StringBuilder generateName() {
        int randomNameNumber = (int)(Math.random() * 6) + 4;
        StringBuilder firstName = new StringBuilder("The ");

        firstName.append(buildName(randomNameNumber, (int)(Math.random()* 15) + 5));
            if((int)(Math.random()*5) >2){
                firstName.append("-");
                randomNameNumber = (int)(Math.random() * 3) + 4;
                firstName.append(buildName(randomNameNumber, (int)(Math.random()* 15) + 5));
            }
            return  firstName;
    }

    private StringBuilder buildName(int randomNameNumber, int vowelConstant) {
        int consonantOrVowel;
        StringBuilder name = new StringBuilder();
        for(int i = 0; i < randomNameNumber; i++){
            consonantOrVowel = (int)(Math.random()*20)+1;
            if(consonantOrVowel >= vowelConstant){
                if(i == 0){
                    name.append(String.valueOf(consonants[(int) (Math.random() * 20)]).toUpperCase());
                }else {
                    name.append(consonants[(int) (Math.random() * 20)]);
                }
            }else{
                if(i == 0){
                    name.append(String.valueOf(vowels[(int)(Math.random()*4)]).toUpperCase());
                }else {
                name.append(vowels[(int)(Math.random()*4)]);
            }
        }
        }
        return name;
    }
}
