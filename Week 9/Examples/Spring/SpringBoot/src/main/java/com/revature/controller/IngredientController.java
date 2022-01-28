package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.BusinessException;
import com.revature.model.Ingredient;
import com.revature.service.IngredientService;

@RestController("ingredientController")
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	/*
	 * The "consumes" attribute specifies which content types you're willing to
	 * accept from the client.
	 * 
	 * As a general note, you can allow clients to negotiate which content type
	 * they'd like to work with.
	 */
	@PostMapping(path = "/new", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public void save(@RequestBody Ingredient ingredient) {
		this.ingredientService.save(ingredient);
	}
	
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ingredient>> findAll(){
		return new ResponseEntity<List<Ingredient>>(
				this.ingredientService.findAll(), HttpStatus.OK);
	}
	
//	@PutMapping(path = "/modified", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void update(@RequestBody Ingredient ingredient) {
//		this.ingredientService.update(ingredient);
//	}
	
	/*
	 * We can also accept path variables. In this case, we will allow the client
	 * to send back an ID as a part of the request URL. We can easily grab that
	 * portion of the URL by using the @PathVariable annotation to grab the id
	 * as a parameter.
	 * 
	 * e.g. /ingredient/id/7 should return the ingredient with the ID 7
	 */
	@GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ingredient findById(@PathVariable int id) {
		return this.ingredientService.findById(id);
	}
	
	/*
	 * If you do not wish to use a path variable, you can also easily access
	 * request parameters (e.g. /ingredient/idv2?id=4?anotherParam=stringtype
	 * 
	 * Note that we're not actually using the second parameter, but it is there
	 * to show that the client can pass back multiple parameters via a query
	 * string and that you can easily retrieve those parameters. Just make sure that
	 * your parameter names match the query parameters.
	 */
	
	@GetMapping(path = "/idv2", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingredient> findByIdV2(@RequestParam int id, @RequestParam String anotherParam) {	
		
		/*
		 * While it's perfectly acceptable to just return whatever object it is that
		 * you want serialized, there is a more eloquent syntax for writing to the
		 * response body. This syntax involves using the ResponseEntity type.
		 */
		return new ResponseEntity<Ingredient>(this.ingredientService.findById(id), HttpStatus.OK);
	}
	
	
	/*
	 * We'll create an endpoint that throws a RuntimeException as proof of concept
	 * for @ExceptionHandler.
	 */
	@GetMapping(path = "/exception-handler")
	public void throwException() {
		throw new RuntimeException();
	}
	
	/*
	 * We'll also create an endpoint that throws a BusinessException (a custom type
	 * we created) as proof of concept for @ResponseStatus.
	 * 
	 * NOTE TO SELF: Find out why the message does not appear alongside the status code.
	 */
	@GetMapping(path = "/response-status")
	public void throwBusinessException() {
		throw new BusinessException();
	}
	
	/*
	 * Let us create an ExceptionHandler method that will send a response to the client
	 * in the case that a specific Exception is thrown on the backend.
	 */
	
//	@ExceptionHandler(Exception.class)
//	public String handleException() {
//		return "Eh. Something went wrong there";
//	}
	
}
