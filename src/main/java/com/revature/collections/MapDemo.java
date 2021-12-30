package com.revature.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    /*
    Technically, Map Doesn't inherit from the Collection interface. Map is its own interface that inherits from an
    older interface called "Dictionary".
     */
    public static void main(String[] args) {
        //Maps follow the generic types Key,Value, or <K,V>
       /*
       Note that maps are groups of key-value pairs. We use a "key" to access an underlying value. Useful for
       usernames and passwords. This works a lot like a dictionary in which you use the word to find it's definition
        */
        Map<String, String> myMap = new HashMap<>();
        /*
        How do you add to a Map? It's .put(Key, Value);
         */
        myMap.put("table", "a place where we hold many things that we dont feel like putting away");
      /*
      Accessing a Map:
       */
        myMap.get("phone");
        //prints null because no such key exists
        myMap.get("table");
        myMap.get("job");
      //  System.out.println(myMap);
        myMap.put("job", "a task completed for money, but hopefully a task you enjoy");
       // System.out.println(myMap);
        /*
        If you add a Key that exists already, you WILL overwrite the existing value for that Value. For ex:
         */
        myMap.put("table", "Something to play tabletop games on");
        //Before this line, "table printed the line starting with "a task"
        //System.out.println(myMap);
        //Will only add to the HashMap if the Key doesn't already exist
        myMap.putIfAbsent("table", "yet another definition");
        /*
        Maps ARE NOT ITERABLE
        Does not compile:
        for (String key : myMap
         */

        /*
        That said, Set is iterable, and there is a method which allows us to get all of the keys in a Map as a Set.
        We can then iterate over the set of keys and find the key that we want in order to access its underlying value
         */
        Set<String> theMapsKeys = myMap.keySet();
        myMap.remove("job");
        for(String key: theMapsKeys){
            if(key.equals("job")){
                System.out.println(myMap.get(key));

                /*
                If you want to remove a key, you can still use .remove();
                 */
            }
        }
    }
}
