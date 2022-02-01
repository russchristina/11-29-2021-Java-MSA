package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.Ingredient;

/*
 * My intention with this interface is simply to declare any methods that I think would
 * be necessary for data access concerning my Ingredient model. I want to be able to query
 * my DB for ingredient records and also insert new ingredient records or even update
 * them. Basically, we'd like to define any basic CRUD here.
 */
@Repository("ingredientRepository")
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
	/**
	 * 
	 * @param id the primary key that is used to locate the ingredient
	 */
	Ingredient findById(int id);
	
	/**
	 * 
	 * @param name the name of the ingredient in the database
	 * 
	 * Note that Spring Data JPA has its own expression language for writing
	 * methods. You can use the name of a field on a type as we have done below,
	 * and Spring Data JPA will know how to implement the method based on the
	 * method name.
	 */
	Ingredient findByName(String name);
	
}
