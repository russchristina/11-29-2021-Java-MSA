package Wilson_Challenges.bin.com.revature.Wilson_Challenges.src.com.revature;

public class PallinWords {
	static boolean isPalDrone(String word) {
		int y=0; int p = word.length() -1;
		while (y<p) {
			if (word.charAt(y) != word.charAt(p))
				return false;
				
				y++;
				p--;
			}
		return true;
	}
public static void main(String[]args) {
	String word1="cat";
	String word2="peep";
	
	System.out.println(word1);
	
	if(isPalDrone(word1))
		System.out.println("This is a palindrone");
	else
		System.out.println("This is not a palindrone");
	
	System.out.println(word2);
		if(isPalDrone(word2))
			System.out.println("This is a palindrone");
		else
			System.out.println("This is not a palindrone");
}
 
	
	
}
