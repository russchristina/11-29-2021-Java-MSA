package com.revature.display.account;

import com.revature.models.shop.Life;
import com.revature.models.shop.generator.MarkovChain;
import com.revature.models.users.User;

public class LifeDisplay {
    public void communicateWithLife(Life lifeForm, User user) {
        MarkovChain markovChain = new MarkovChain();
        System.out.println();
        for (int i = 0; i < 100; i++) System.out.print("=");

        System.out.println();
        System.out.println("INCOMING TRANSMISSION...\n");
        System.out.println("\n...TRANSLATING TO ENGLISH...\n");

        for (int i = 0; i < 100; i++) System.out.print("=");

        System.out.println();

        System.out.println(markovChain.generateParagraph());

        for (int i = 0; i < 100; i++) System.out.print("=");
        System.out.println();
        System.out.println("...END TRANSMISSION\n");
        System.out.println();
        for (int i = 0; i < 100; i++) System.out.print("=");
        System.out.println();
    }
}
