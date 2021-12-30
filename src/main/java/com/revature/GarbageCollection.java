package com.revature;//package com.revature;
//
//import com.revature.nonaccessmodifiers.StaticModifier;
//
///*
//Java has built-in memory management; this means that Java handles the cleanup of unused objects
//
// */
//public class GarbageCollection {
//    public static void main(String[] args) {
//        StaticModifier sm = new StaticModifier();
//        StaticModifier sm2 = new StaticModifier();
//        sm = sm2; /*
//        sm point to whatever object sm2 points to now; at this point, the object that sm pointed to becomes eligible for
//        garbage collection because there are no references to it anymore. Please not the that we use the word eligible
//        here because GC in Java is not guaranteed. In any case, Java's built-in GC can choose to collect this object now,
//        but you CANNOT force this garbage collection.
//    }
//    /*
//    How does Java know when it is safe to clean up an object in memory *destroy an object*
//     */
//   /*
//   How does Java know when iti is safe to clean up an object in memory (destroy an object) in order for
//   an object to become eligible for garbage collection in Java, there must be no references to that object
//    */
//System.gc(); //requests garbage collection. BAD PRACTICE
//    }
//}