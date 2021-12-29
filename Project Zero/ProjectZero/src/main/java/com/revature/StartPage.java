package com.revature;

import java.util.Scanner;




public class StartPage {

	
	Scanner scanner = new Scanner(System.in);
	
public static void main(String[] args) {
	
	
	MainPage MainPageObj = new MainPage();
	MainPageObj.displayChoices();
	
	
	LoginPage LoginPageObj = new LoginPage();
	LoginPageObj.userLogin();
	
	
	AccountTransactions AccountTransactionsObj = new AccountTransactions();
	AccountTransactionsObj.showTransactionMenu();
	AccountTransactionsObj.showTransactions();
	
	
	MooVSelection MooVSelectionObj = new MooVSelection();
	MooVSelectionObj.userMooVSelection();
	
}

}
