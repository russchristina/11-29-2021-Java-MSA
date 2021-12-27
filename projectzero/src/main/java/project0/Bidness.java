package project0;

import java.util.Scanner;

import project0.implement.ItemRepoImpl;
import project0.implement.UserRepoImpl;
import project0.repos.ItemRepo;
import project0.repos.UserRepo;


public class Bidness extends Account{
	static Bidness b = new Bidness();
	
	public static void main(String[] args) {
		
		ItemRepo itemRepo = new ItemRepoImpl();
		
		UserRepo userRepo = new UserRepoImpl();
		
		//System.out.println(itemRepo.findAll());
		
		//System.out.println(itemRepo.findById(2));
		
		b.login();
		
		System.out.println(userRepo.findAll());
		
	}
	
	
	
	
	
	
	
	
		


}
