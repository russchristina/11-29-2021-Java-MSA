// I am writing this code in Vim after writing the other 2 in STS

/*
 * Write a method that returns the absolute value of the difference of any two integers.
 */

public class AbsoluteValue{

	// I declare a method to subtract two integers
	public int Minus(){
	
	//then i declare my variables
	int c = 5;
	int d = 7;
	int e = c - d;
	
	
	//then i create a condition to only show the absolute value, no negative values
	if(e < 0){
		System.out.println(d - c);
	}
	else{
		System.out.println(c - d);
	}
	
}
}
