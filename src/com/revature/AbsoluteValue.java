package Wilson_Challenges.bin.com.revature.Wilson_Challenges.src.com.revature;

public class AbsoluteValue {
	
	public static void main(String []args) {
		AbsoluteValue absoluteValue = new AbsoluteValue();
		
		System.out.println(absoluteValue.calculateDifAbsoluteValue(-3,7));
	}
public int calculateDifAbsoluteValue(int num1, int num2) {
	Math.abs(num1);
	
	
	
	int difference= num1 -num2;
	
	if (difference>=0) 
		return difference;
		
	else
		return difference *-1;
			
	}
}
