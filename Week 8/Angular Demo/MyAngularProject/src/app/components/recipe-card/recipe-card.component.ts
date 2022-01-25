import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.css']
})
export class RecipeCardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  /**
   * Remember that an Angular component is a class. This means that a component can have its own state. We can take advantage of this state by accessing it from the view.
   * 
   * Note that this state is passed down from the parent component. Also note that the name of the property should match the name of the property that is specified at the level of the parent component. For instance, this property is called "recipe" and the property is bound at the parent level is also called "recipe". Also note the use of "Input" decorator to pass this state down.
   */

   @Input()
   recipe:Recipe;

   isHidden:boolean = true;

  /**
   * If I'm going to use event binding in Angular, I still need to specify the callback function that should be invoked when an event occurs on the frontend.
   */

   toggleVisibility():void{
    this.isHidden = !this.isHidden
   }
}
