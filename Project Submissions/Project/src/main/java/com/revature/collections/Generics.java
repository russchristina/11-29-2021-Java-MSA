package com.revature.collections;

import java.util.ArrayList;
import java.util.List;

public class Generics {

    public static void main(String...args){
        /*Collections should often be compiled with a generic. Generics are placeholders for a type that will be
        listed. Must be a class; use wrapper classes such as String, Integer, Double, etc. in place of a generic.
        This adds 'Compile-Time Safety', because the code will not compile if you pass in a variable outside of
        of the listed Generics

         .*
         We can int literals using the add method even though collections don't take primitives. THis is allowed
         because Java "autoboxes" primitieves as their object representations for us. You SHOULD NOT box primitives
         on your own as it is bad practice

         */
        List<String> myList = new ArrayList<>();
        //Java will also unbox values for you
        Integer num1 = 2;
        Integer num2 =4;
        int result = num1 + num2;
        //It shouldnt work because those aren't primitives, but Java understands what you're trying to do so it'll
        //unbox for you
        String a = "Hello";
    }
}
