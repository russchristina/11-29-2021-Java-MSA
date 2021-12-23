package com.revature.nonaccessmodifiers;

public class StaticModifier {
    /*
    The "static" modifer can be applied to fields and methods. It can be applied to inner/nested classes; not
    relevant for now
     */
    public int staticModifierInt;
    public String staticModifierString;
    //static. ALL THAT'S NOT ALLOWED IS CALLING A NON STATIC VARIABLE FROM A STATIC METHOD
    public static int actuallyAStaticInt;

    public static void main(String[] args) {
        StaticModifier statmod = new StaticModifier();
        beStatic();
        beStatic();

    }
    /*
    When a method is static, you do NOT need an instance of the class in which it exists to call it. In fact,
    it is bad practice to use an instance of the class to call it.
     */

    public static void beStatic() {
        /*
        If you have a static method or field, you don't need to create an onject/instance to access
        that method or field. This is because anything that is static is available the minute the app starts running.

        Static methods are avaialable as soon as the program runs, whereas instance variables are available once
        a new instance of the class is called. So you cannot access non static items from a static method, because
        they don't exist
         */
//        staticModifierInt = 0;
//        this.staticModifierString = "Hello";
        //static can be accessed anywhere
//        actuallyAStaticInt = 4;
    }

    /*
    This is not a static method.
    maybe make a static class in MainDisplay, and call the method from GetKey
     */
    public void doNotBeStatic() {
        beStatic();
        actuallyAStaticInt = 5;
    }
}

