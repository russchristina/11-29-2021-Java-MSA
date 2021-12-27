package my.app;

import java.util.Scanner;

public class Driver {
	static UX ux = new UX();
	static AccessAccounts aa = new AccessAccounts();
	static Account account = new Account();
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
			ux.entry();
	}
}
