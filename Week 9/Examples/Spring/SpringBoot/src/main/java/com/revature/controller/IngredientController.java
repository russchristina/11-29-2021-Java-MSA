package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.revature.service.IngredientService;

@Controller("ingredientController")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	
	
	
	
}
