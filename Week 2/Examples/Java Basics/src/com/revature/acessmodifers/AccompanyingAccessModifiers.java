package com.revature.acessmodifers;

public class AccompanyingAccessModifiers {

	public static void main(String[] args) {
		AccessModifiers accessModifiers = new AccessModifiers();
		//Direct access is allowed for this protected member because this class is in the
		//same package as the AccessModifiers class.
		accessModifiers.fieldTwo = "another string";
		accessModifiers.fieldThree = false;
	}
}
