package Homewrok;

public class Puppy {
String name;
String breed;
int age;

public Puppy(String pName, String pBreed, int pAge)
{
	name = pName;
	breed = pBreed;
	age = pAge;
	
}
	public static void main(String[] args) {
Puppy puppy1 = new Puppy("chester","pug", 3);
System.out.println(puppy1.name);
System.out.println(puppy1.breed);
System.out.println(puppy1.age);
	}

}
