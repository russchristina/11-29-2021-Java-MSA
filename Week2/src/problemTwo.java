
public class problemTwo {

	
	static int fibonacci(int x) {
		if (x<1)
			return x;
		return fibonacci(x-1)+fibonacci(x-2);
	}
	
	
	public static void main(String[] args) {
	int x = 21;
	System.out.print(fibonacci(x));

	}

}
