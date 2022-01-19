import { Component, OnInit } from '@angular/core';
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
   */

   recipe:Recipe = new Recipe(1, 'Generic Brand Ribs', 'These ribs are really generic.', 'https://image.shutterstock.com/image-photo/spicy-hot-grilled-spare-ribs-600w-611174102.jpg', ['step 1', 'step 2'], 'christina');

   isHidden:boolean = false;

  /**
   * If I'm going to use event binding in Angular, I still need to specify the callback function that should be invoked when an event occurs on the frontend.
   */

   toggleVisibility():void{
    this.isHidden = !this.isHidden
   }
}
