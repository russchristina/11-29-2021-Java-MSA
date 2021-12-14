package com.revature.nonaccessmodifiers;

public class StaticCompanionClass {

	public static void main(String[] args) {
		
		/*
		 * We can just use the class name to access static members.
		 */
		StaticModifier.actuallyAStaticInt = 0;
		
		StaticModifier sm1 = new StaticModifier();
		System.out.println(sm1.actuallyAStaticInt);
		System.out.println(sm1.staticModifierInt);
		
		StaticModifier sm2 = new StaticModifier();
		System.out.println(sm2.actuallyAStaticInt);
		System.out.println(sm2.staticModifierInt);
		
		StaticModifier sm3 = new StaticModifier();
		System.out.println(sm3.actuallyAStaticInt);
		System.out.println(sm3.staticModifierInt);
	}
}
