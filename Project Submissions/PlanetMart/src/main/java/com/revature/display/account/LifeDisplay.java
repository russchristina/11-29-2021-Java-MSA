package com.revature.display.account;

import com.revature.models.shop.Life;
import com.revature.models.users.User;

public class LifeDisplay {
    public void communicateWithLife(Life lifeForm, User user) {
        for (int i = 0; i < 100; i++) System.out.print("=");
        System.out.println();
        System.out.println(".         _  .          .          .    +     .          .          .      .\n" +
                "        .(_)          .            .            .            .       :\n" +
                "        .   .      .    .     .     .    .      .   .      . .  .  -+-        .\n" +
                "          .           .   .        .           .          /         :  .\n" +
                "    . .        .  .      / .   .      .    .     .     .  / .      . ' .\n" +
                "        .  +       .    /     .          .          .   /      .\n" +
                "       .            .  /         .            .        *   .         .     .\n" +
                "      .   .      .    *     .     .    .      .   .       .  .\n" +
                "          .           .           .           .           .         +  .\n" +
                "  . .        .  .       .   .      .    .     .     .    .      .   .\n" +
                "\n" +
                " .   +      .          ___/\\_._/~~\\_...__/\\__.._._/~\\        .         .   .\n" +
                "       .          _.--'                              `--./\\          .   .\n" +
                "           /~~\\/~\\                                         `-/~\\_            .\n" +
                " .      .-'                                                      `-/\\_\n" +
                "  _/\\.-'                                                          __/~\\/\\-.__\n" +
                ".'                                                                           `.dp");

        System.out.println();
        for (int i = 0; i < 100; i++) System.out.print("=");

        System.out.println();
        System.out.println("INCOMING TRANSMISSION\n");
        System.out.println("\nTRANSLATING TO ENGLISH\n");
        System.out.println();

        for (int i = 0; i < 100; i++) System.out.print("=");

        System.out.println();

        System.out.println(lifeForm.getCommunication());

        for (int i = 0; i < 100; i++) System.out.print("=");
        System.out.println();
        System.out.println("...END TRANSMISSION\n");
        System.out.println();
        for (int i = 0; i < 100; i++) System.out.print("=");

    }
}
