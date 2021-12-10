package Wilson_Challenges.bin.com.revature.Wilson_Challenges.src.com.revature;

public class Sum {
	
	public static void main(String []args) {
		Sum sumValue = new Sum();
		System.out.println(sumValue.add(1,2,3,4,5));
	
	

}

public int add(int...numbers) {
		
		int sum=0;
		
		for(int i:numbers) {
		sum +=i;
	}
		return sum;
	}
}
