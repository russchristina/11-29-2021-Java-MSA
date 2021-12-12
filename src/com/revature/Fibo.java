package Wilson_Challenges.bin.com.revature.Wilson_Challenges.src.com.revature;

public class Fibo {
	static int fibonac(int y) {
		if(y<=1)
			return y;
		return fibonac(y-1) + fibonac(y-2);
	}
	
	public static void main(String[]args) {
		
		System.out.println(fibonac(5));
	}
		
	

}
