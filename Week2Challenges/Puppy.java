package com.revature;
//Starting our Puppy class that will contain all general features 
public class Puppy{
	// Declaring the behaviors and values 
	int Age;
	double Speed;
	// Setting a value to be by default for instance 
	boolean Breathe=true;
	String Name; 
	String Breed; 
public static Puppy PuppyCreator(String name,int age,double speed,String breed){
	//creating an instance of puppy class 
	Puppy myPuppy=new Puppy();
	//setting the instance values from the method args. 
	myPuppy.Name=name;
	myPuppy.Age=age;
	myPuppy.Breed=breed;
	myPuppy.Speed=speed;
	
	return myPuppy;
	

	};
	// The main method
	public static void main(String[] args) {
		// Storing our method return in myDog var
		// Creating our Dog 
	  Puppy	myDog= PuppyCreator("Oliver", 3, 9.5, "German Shepard");
	   //printing our created instance 
	  System.out.println(myDog);
		System.out.println(myDog.Name);
		System.out.println(myDog.Age);
		System.out.println(myDog.Speed);
		System.out.println(myDog.Breed);
		// we didn't have to set this value
		System.out.println(myDog.Breathe);
		
	};
	
	
	
	
	
};

