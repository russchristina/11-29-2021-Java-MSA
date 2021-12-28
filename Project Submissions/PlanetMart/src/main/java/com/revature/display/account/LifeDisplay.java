package com.revature.display.account;

import com.revature.display.utility.CreateShapes;
import com.revature.models.shop.Life;
import com.revature.models.shop.generator.MarkovChain;
import com.revature.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class LifeDisplay {

    CreateShapes createShapes = new CreateShapes();
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    public void communicateWithLife(Life lifeForm, User user) {

        if(lifeForm == null){
            System.out.println(createShapes.indent + "NO LIFE DETECTED");
            return;
        }

        MarkovChain markovChain = new MarkovChain();
        System.out.println();
        System.out.println(createShapes.border);

        System.out.println();
        System.out.println(createShapes.indent + "INCOMING TRANSMISSION...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(createShapes.indent + "...TRANSLATING TO ENGLISH...");

        System.out.println();
        markovChain.generateParagraph();

        int randomAlien = (int)(Math.random()*3);

        File markovText = new File("src/main/resources/MarkovText.txt");
        File alien = new File("src/main/resources/Alien"+ randomAlien +".txt");

        try(FileReader fileReader = new FileReader(markovText);
            FileReader alienArt = new FileReader(alien);
            BufferedReader bufferedReaderAlien = new BufferedReader(alienArt);
            BufferedReader bufferedReader = new BufferedReader(fileReader))
            {

                System.out.println(createShapes.border);

                while (bufferedReaderAlien.ready()){
                    System.out.println(createShapes.shortIndent + bufferedReaderAlien.readLine());
                    Thread.sleep(20);
                }
                System.out.println();
                System.out.println(createShapes.indent + "Hello... " + user.getName());
                System.out.println(createShapes.indent + "... we " + "... " + lifeForm.getName() + " ...");
                System.out.println();
                Thread.sleep(100);
                while(bufferedReader.ready()){
                System.out.println(createShapes.shortIndent + bufferedReader.readLine());
                }

        } catch (FileNotFoundException e) {
            errorLogger.error(String.valueOf(e));
        } catch (IOException e) {
            errorLogger.error(String.valueOf(e));
        } catch (InterruptedException e) {
            errorLogger.error(String.valueOf(e));
        }

//        System.out.println(markovChain.generateParagraph());

        System.out.println();
        System.out.println(createShapes.border);
        System.out.println();
        System.out.println(createShapes.indent + "...END TRANSMISSION");
        System.out.println();
        System.out.println(createShapes.border);
        System.out.println();
    }
}
