package com.revature.repository;

import java.util.List;

import com.revature.model.Ingredient;

/*
 * My intention with this interface is simply to declare any methods that I think would
 * be necessary for data access concerning my Ingredient model. I want to be able to query
 * my DB for ingredient records and also insert new ingredient records or even update
 * them. Basically, we'd like to define any basic CRUD here.
 */
public interface IngredientRepository {

	/**
	 * 
	 * @param ingredient an ingredient that will be persisted to database
	 */
	void save(Ingredient ingredient);
	
	/**
	 * 
	 * @param id the primary key that is used to locate the ingredient
	 */
	Ingredient findById(int id);
	
	/**
	 * 
	 * @param name the name of the ingredient in the database
	 */
	Ingredient findByName(String name);
	
	/**
	 * This method locates every single ingredient in our database
	 */
	List<Ingredient> findAll();
	
	/**
	 * 
	 * @param ingredient the ingredient that will be update in our database
	 */
	void update(Ingredient ingredient);
	
	/**
	 * 
	 * @param ingredient the ingredient that will be deleted from the database
	 */
	void delete(Ingredient ingredient);
	
}
