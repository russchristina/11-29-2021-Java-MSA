package Com.revature;

import java.util.ArrayList;
import Com.revature.*;
import java.util.Scanner;
public class Customer 
{

private static final double Storecred = 0;
static ArrayList<String>  Gbag = new ArrayList<String>(10); 


	public static void main(String[] args) {

		
		
		

	}
	
	
	public static void buyGames() 
	{
		
		
		
		Scanner sc = new Scanner(System.in);
		int choice1 = 0;
	
		System.out.println("Please choose a game");
		System.out.println("Press 1 for Farcry5");
		System.out.println("Press 2 for COD13");
		System.out.println("Press 3 for Perfect Dark");
		System.out.println("Press 4 for Madden29");
		System.out.println("Press 5 for Nba29");
		System.out.println("Press 6 for Rampage");
		System.out.println("Press 7 for Arma2029");
		System.out.println("Press 8 for Wolfenstein");
		System.out.println("Enter checkout area by pressing 9");
		System.out.println("Exit");
			choice1 = sc.nextInt();
		
	
		if(choice1 == 1)
		{
			Gbag.add("Farcry5");
			System.out.println("Game added");
			buyGames();
			System.out.print(Gbag);
			
			
			
		
		}	
		if(choice1 == 2)
		{
			Gbag.add("COD13");
			System.out.println("Game added");
			System.out.print(Gbag);
			buyGames();
			
		}
		if(choice1 == 3)
		{
			Gbag.add("Perfect Dark");
			System.out.println("Game added");
			System.out.print(Gbag);
			buyGames();
		}
		if(choice1 == 4)
		{
			Gbag.add("Madden 29");
			System.out.println("Game added");
			System.out.print(Gbag);
			buyGames();
		}
		if(choice1 == 5)
		{
			Gbag.add("NBA29 ");
			System.out.println("Game added");
			System.out.print(Gbag);
			buyGames();
		}
		if(choice1 == 6)
		{
			Gbag.add("Rampage");
			System.out.println("Game added");
			System.out.print(Gbag);
			buyGames();
		}
		if(choice1 == 7)
		{
			Gbag.add("Arma2029");
			System.out.println("Game added");
			System.out.print(Gbag);
			buyGames();
		}
		if(choice1 == 8)
		{
			Gbag.add("Wolfenstein");
			System.out.println("Game added");
			System.out.print(Gbag);
			buyGames();
		}
		else if(choice1 == 9)
		{
			
			System.out.println("See ya in checkout");
			checkOut();
		}
		sc.close();
	}
	
	static void checkOut()
	{
	
	
	double price;
	double subtotal;
	double stateTax = 0.0495;	
	double totalTax;
    
	price =	Gbag.size() * 60;
	totalTax = price * stateTax;
	subtotal = price + totalTax - Storecred;
	System.out.println("Yor Total Store credit is ----" + Storecred);                     
	System.out.println("Printing receipt-------- ") ;
	System.out.println("The price of the games without tax ----" + price);
	System.out.println("The total taxes is  ----" + totalTax);
	System.out.println("The subtotal is------ " + subtotal);
	 
	
	
	
	
	}
	
	public static void SellGame()
	{   Scanner tc = new Scanner(System.in);
		String SelGame = "";
 		System.out.println("What games are you trying to sell");
		SelGame = tc.next();
		System.out.println(SelGame);
		System.out.println("---Has been sold for 30 dollars worth of store credit");
		
		
		
	}

}
