package Com.revature;
import Com.revature.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main extends Customer {
int wallet = 0;
	public static void main(String[] args) {
		Main M = new Main();
		
		Scanner sc = new Scanner(System.in);
				 
				ArrayList<String>  forsale = new ArrayList<String>(9);
				ArrayList<String>  Gbag = new ArrayList<String>(10); 
				
				forsale.add("Farcry5");
				forsale.add("COD13");
				forsale.add("Perfect Dark");
				forsale.add("Madden29");
				forsale.add("NBA29");
				forsale.add("Rampage");
				forsale.add("Arma2029");
			    forsale.add("Wolfenstein");	
			    
			    String user = "";
			    String password = "";

    
		System.out.println("Press one to see all the games we have---");
		System.out.println("Press two to buy some games----");
		System.out.println("Press three to Enter as a employee---");
		System.out.println("Press four to Sell your game to the store---");
		System.out.println("Press five to go straight to Checkout---");
		int choice = 3 ;
		int choice1 = 3;
		choice = sc.nextInt();
		int i = 0;
		switch(choice)
		{
		case 1:
			System.out.println("Showing inventory");
			System.out.println(forsale);
			M.main(args);
		break;
		
		case 2:
			buyGames();
            break;
		case 3:
		Employee.welcome();
		
		break;
		case 4:
			SellGame();
		M.main(args);
		case 5:
			checkOut();
			
		break;
		}
if(choice > 6)
{
	System.out.println("This is not a valid option");
	System.out.println("Sending you back to the Main Menu ");
    	M.main(args);
	}
    

	sc.close();	
	}
	

	}


	

	





