package Com.revature;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee  {
	private static double Storecred = 0;
	
	
	
	   static ArrayList<String> welcome()
	{ Main M = new Main();
		  ArrayList<String> forsale = new ArrayList<String>();
		 
		forsale.add("Farcry5");
		forsale.add("COD13");
		forsale.add("Perfect Dark");
		forsale.add("Madden29");
		forsale.add("NBA29");
		forsale.add("Rampage");
		forsale.add("Arma2029");
	    forsale.add("Wolfenstein");	  
		
				
	System.out.println("Welcome employee to the management center");
	System.out.println("Press one to add a game");
	System.out.println("Press two to remove a game");
	System.out.println("Press three to sell the store a game");
	
	int pick = 0;
	Scanner sc = new Scanner(System.in);
	pick = sc.nextInt();
	if( pick == 1)
	{
		System.out.println("Please enter the new game to inventory");
		String newGame = sc.next();
		
		
		forsale.add(newGame);
		System.out.println("The new list of games are ----" + forsale);
	
	}

	 
	if( pick == 2)
	{	
	 System.out.println("Press one to remove farcry5");
	 System.out.println("Press two to remove COD13");
	 System.out.println("Press three to remove Perfect Dark");
	 System.out.println("Press four to remove Madden29");
	 System.out.println("Press five to remove NBA29");
	 System.out.println("Press six to remove Rampage");
	 System.out.println("Press seven to remove Arma2029");
	 System.out.println("Press eight to remove Wolfenstein");
	 System.out.println("Press nine to go back");
	 int delePick = 0;
		delePick = sc.nextInt();
	if(delePick == 1)
	{
		System.out.println("you have removed " + forsale.get(0));
		forsale.remove(0);
		System.out.println(forsale);
		welcome();
		
		
	}
	if (delePick == 2)
	{
		System.out.println("you have removed " + forsale.get(1));
		forsale.remove(1);
		System.out.println(forsale);
		welcome();
	}
	 if(delePick == 3)
	 {
			System.out.println("you have removed " + forsale.get(2));
			forsale.remove(2);
			System.out.println(forsale);
			welcome();
	 }
	 if(delePick == 4)
	 {
			System.out.println("you have removed " + forsale.get(3));
			forsale.remove(3);
			System.out.println(forsale);
			welcome();
	 }
	 if(delePick == 5)
	 {
			System.out.println("you have removed " + forsale.get(4));
			forsale.remove(4);
			System.out.println(forsale);
			welcome();
	 }
	 if(delePick == 6)
	 {
			System.out.println("you have removed " + forsale.get(5));
			forsale.remove(5);
			System.out.println(forsale);
			welcome();
	 }
	 if(delePick == 7)
	 {
		System.out.println("you have removed " + forsale.get(6));
		forsale.remove(6);
		System.out.println(forsale);
		welcome();
	}
	if(delePick == 8)
	{
		System.out.println("you have removed " + forsale.get(7));
		forsale.remove(7);
		System.out.println(forsale);
		welcome();
	}
	if(delePick == 9)
	{

		welcome();
	}
	
	if(pick == 3)
	{	
		System.out.println("What games are you trying to sell");
		   Scanner tc = new Scanner(System.in);
		   String SelGame = "";
		   SelGame = tc.next();
		   forsale.add(SelGame);
		 Storecred = Storecred + 30;
		 M.main(null);
		
     }

		
	}

	sc.close();


	return forsale;
	
	
	}


	}
