package com.revature.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
When someone asks you about the "Collections Framework", they're generally asking about:
1) The Collection interface/hierarchy
2) The Collections utlity class
The collections utility is just a way of organizing the list
 */
public class CollectionsUtility {
    public static void main(String[] args) {
        List<String> firstNames = new ArrayList<>();
    firstNames.add("Meezy");
        firstNames.add("Ecole");
        firstNames.add("PWong");
        firstNames.add("Lefty");

        //Print out the alphabetically highest and lowest names
        System.out.println(Collections.max(firstNames));
        System.out.println(Collections.min(firstNames));

        System.out.println("-------------------------------------------------------");
        //Sort alphabetically
        System.out.println(firstNames);
        Collections.sort(firstNames);
        System.out.println(firstNames);
        System.out.println("-------------------------------------------------------");
        //shuffle
        Collections.shuffle(firstNames);
        System.out.println(firstNames);
        Collections.shuffle(firstNames);
        System.out.println(firstNames);
        System.out.println("first place prize goes to: " + firstNames.get(0));

    }

}
