import { Injectable } from '@angular/core';
import { Recipe } from '../models/recipe';
import {HttpClient, HttpHandler, HttpHeaders} from '@angular/common/http'
import { Observable } from 'rxjs';

/**
 * A service is a class that is decorated with the @Injectable decorator. This marks this class as a candidate for dependency injection.
 */
@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  /**
   * We're going to be using this service to build out methods which make HTTP requests for recipes. This means that we will need some utility for making HTTP requests. Fortunately, Angular has a built-in module for making HTTP requests that is easy to use and simpler than AJAX. This is called the HttpClientModule. To make things even better, the HttpClient type that is included in the module is @Injectable.
   */
  constructor(private httpClient:HttpClient) { }

  /**
   * Let's make a method that grabs all of the recipes from my (hopefully) up and running backend.
   * 
   * Note that this HTTP request will be made asynchronously. This means that you're dealing with eventual values. In Angular, we use Observables to represent eventual values. Observables are similar to Promises in that they provide a way of handling asynchronous data, but...
   * 
   * 1) Observables are cancelable.
   * 2) Observables are resuable; observables act like streams of data. Promises, on the other hand, are not reusable.
   */
  public findAllRecipes():Observable<Recipe[]>{

    /**
     * As a general note, if you are going to return all of your recipes as a Recipe[], you better make sure that your frontend model for recipe matches the backend model for recipe.
     */
    return this.httpClient.get('http://localhost:8000/recipe/all') as Observable<Recipe[]>
  }

}
