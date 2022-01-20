import java.util.Objects;

public class Test {
public static void main(String[] args) {
	System.out.println(firstName.hash());
}
private String firstName;

public int hashCode() {
	Object firstName="Bonny";
	return Objects.hash(firstName);
}
	String printName() { 
	return (String) firstName;
	}

}
