package com.revature.nonaccessmodifiers;

import java.util.ArrayList;
import java.util.List;

public class FinalModifier {
    /*
       IF you are using final with an instance variable, note that the code will not compile if you do not
       initialize the variable either:
       1) inline as you're declaring the variable
       2) in the constructor for the class
       3) in an instance initializer (curly braces)
        */
    int a;
//    final int b;
    final int c = 0;
    public static void main(String[] args) {
        final List<String> myString = new ArrayList<>();
    /*
    Notice that I can add to my final ArrayList, thereby chaning its size. This is acceptable.

     */
        myString.add("String1");
        myString.add("String2");
        myString.add("String3");
        /*
        This following code will not compile, because the list is marked as final. We cannot create a new instance
        of an object if its final. Same goes for methods. WE cannot override a final method, but we can still add
         */
//        myString = new ArrayList<>();


    }

}
