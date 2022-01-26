package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.revature.controller.RecipeController;
import com.revature.util.AppConfiguration;


public class Driver {

	public static void main(String[] args) {
		
		/*
		 * Let's prove that our recipeController bean is present in the IOC container.
		 * 
		 * We first used an XML application context.
		 */
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		/*
		 * Now we'll transition to a more annotation based configuration.
		 */
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		
		RecipeController recipeController = context.getBean("recipeController2", RecipeController.class);
		
		System.out.println(recipeController.findAll());
	}
}
