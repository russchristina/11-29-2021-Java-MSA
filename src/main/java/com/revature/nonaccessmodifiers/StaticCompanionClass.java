package com.revature.nonaccessmodifiers;

public class StaticCompanionClass {
    public static void main(String[] args) {
       //no need to do this
        StaticModifier.actuallyAStaticInt = 1;

        /*
        Proving that this is the same instance of actuallyAStaticInt across all methods.
        Even though we declare a new variable each time, the same object is being referenced. So if you run sm3,
        which just does actuallyAStaticInt++, it will print 3
         */
        StaticModifier sm1 = new StaticModifier();
        System.out.println(StaticModifier.actuallyAStaticInt);
        System.out.println(sm1.staticModifierInt);

        StaticModifier sm2 = new StaticModifier();
        System.out.println(StaticModifier.actuallyAStaticInt);
        System.out.println(sm2.staticModifierInt);

        StaticModifier sm3 = new StaticModifier();
        System.out.println(StaticModifier.actuallyAStaticInt);
        System.out.println(sm3.staticModifierInt);
    }
}

