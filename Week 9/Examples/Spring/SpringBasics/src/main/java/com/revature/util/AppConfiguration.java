package com.revature.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.revature.controller.RecipeController;

/*
 * You can additionally add custom beans (e.g. highly customized instances of built-in Spring types)
 * to your IOC container via a Configuration class. Note that we use the @Configuration annotation
 * above this class.
 */

@Configuration
/*
 * This is the equivalent of the 
 * <context:component-scan base-package="com.revature"></context:component-scan> tag.
 */
@ComponentScan(basePackages = "com.revature")
public class AppConfiguration {

	/*
	 * This annotation denotes that this method returns a bean that should be managed by the IOC container.
	 */
	@Bean(name = "recipeController2")
	public RecipeController getRecipeController() {
		//Note that using a configuration class a for simple that requires little configuration is not
		//a great use of a configuration class.
		return new RecipeController();
	}
	
}
