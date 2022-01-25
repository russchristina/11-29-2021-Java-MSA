import { Component, OnInit } from '@angular/core';
import {Recipe} from '../../models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

  /**
   * 
   * Using this syntax allows Angular inject dependencies on our behalf. Just so we're clear, we never use the "new" keyword to create our own instances when Angular injects our dependencies for us.
   */
  constructor(private recipeService:RecipeService) { }

  ngOnInit(): void {
    this.findAllRecipes()
  }

  /**
   * Let's create an array of recipes here. Note that our array is now going to be populated with recipes from our Javalin API.
   */
  recipes:Recipe[] = [];

  /**
   * As a matter of abstraction (in order to keep the ngOnInit method from gettint too bloated), I usually handle calling my service methods from within another method.
   */
  public findAllRecipes(){
    /**
     * In order to access the data inside of the Observable stream, you must "subscribe" to the observable. We'll pass two callback functions to the subscribe method, one for accessing the data, the other for error handling
     */
    this.recipeService.findAllRecipes().subscribe(
      (data) => {
        this.recipes = data
        console.log(this.recipes)
      },
      /**
       * This second function is an optional error handler just in case something should go wrong with your request.
       */
      () => {
        console.log("Ooops something went wrong!");
      }
    )
  }


  //  recipes:Recipe[] = [new Recipe(1, 'Yams', 'sweet potatoes', 'n/a', ['step 1', 'step 2'], 'christina'),
  //  new Recipe(2, 'Cheesecake', 'cheesecake with no particular flavor', 'https://pixnio.com/free-images/2017/09/20/2017-09-20-07-02-00.jpg', ['step 1', 'step 2'], 'christina'),
  //  new Recipe(3, 'Turkey Sandwich', 'unremarkable sandwich', 'https://image.shutterstock.com/image-photo/homemade-sandwich-smoked-turkey-lettuce-600w-1095369245.jpg', ['step 1', 'step 2'], 'christina'),
  //  new Recipe(4, 'Yams', 'sweet potatoes', 'n/a', ['step 1', 'step 2'], 'christina')];
}
