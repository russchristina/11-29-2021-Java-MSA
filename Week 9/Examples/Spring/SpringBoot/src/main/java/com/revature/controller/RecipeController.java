package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Author;
import com.revature.model.Recipe;
import com.revature.service.RecipeService;

/*
 * In order to build a RESTful API using Spring, we use the Spring Web module.
 * The Spring Web module allows us to use simple annotations to create and
 * expose resources to the client.
 * 
 * Note that we are using a specialized Controller that is responsible for writing
 * to the ResponseBody. We call this a RestController. @RestController is a combination
 * of @Controller (which returns views to the client) and @ResponseBody.
 */

@RestController("recipeController")
/*
 * A controller should be mapped to a specific "path". This is the equivalent of
 * building a route in Javalin. Note that we will build subroutes with individual
 * methods in this class.
 */
@RequestMapping("/recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	/*
	 * What if I wanted to build an endpoint that allowed me to expose all recipes
	 * to the client?
	 * 
	 * We would define a method and annotate it with some variation of RequestMapping.
	 * You would then just return whatever value you wanted to be serialized by the 
	 * framework (as returned values are automatically serialized as JSON in this
	 * case). Note that we must specify the subroute here as well. In order to
	 * access this resource, the client must use "/recipe/all".
	 * 
	 * The "produces" attribute allows us to specify what content type is returned
	 * to the client.
	 */
	
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	/*
	 * The @ResponseBody annotation denotes that this method should write to the
	 * response body instead of sending a view back to the client.
	 */
	// @ResponseBody COMMENTED OUT BECAUSE THE ENTIRE CONTROLLER IS NOW A REST CONTROLLER
	public List<Recipe> findAll(){
		return this.recipeService.findAll();
	}
	
	// The @RequestBody body annotation denotes that our parameter is actually
	//pulled directly the request's body. The JSON in the request body is deserialized
	//for us.
	@PostMapping("/new")
	public void save(@RequestBody Recipe recipe) {
		System.out.println(recipe);
		this.recipeService.save(recipe);
	}
	
	@PostMapping("/author")
	public List<Recipe> findAllByAuthor(@RequestBody Author author){
		return this.recipeService.findAllByAuthor(author);
	}
	
	@GetMapping("/cooktimelessthan/{cooktimeinminutes}")
	public List<Recipe> findAllByCooktimeinminutesLessThan(@PathVariable int cooktimeinminutes){
		return this.recipeService.findAllByCooktimeinminutesLessThan(cooktimeinminutes);
	}
	
	
	
}
