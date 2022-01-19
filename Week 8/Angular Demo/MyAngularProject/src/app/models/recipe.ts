import { EmptyError } from 'rxjs';

/**
 * In TS (TypeScript), you can create classes. 
 */
export class Recipe{

    //Declaring fields with their types as TS has static typing. As a general note, you can have "public", "protected", or "private" fields and methods in TS.
    // id:number;
    // name:string;
    // description:string;
    // img_url:string;
    // recipe_steps:string[];
    // author_name:string;

    // To create a constructor:
    // constructor(id:number, name:string, description:string, image_url:string, recipe_steps:string[], author_name:string){
    //     this.id = id;
    //     this.name = name;
    //     this.description = description;
    //     this.img_url = image_url;
    //     this.recipe_steps = recipe_steps;
    //     this.author_name = author_name;
    // }

    //Alternatively, you can declare you variables within your parameter list like so:
    constructor(public id:number, public name:string, public description:string, public img_url:string, public recipe_steps:string[], public author_name:string){
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.recipe_steps = recipe_steps;
        this.author_name = author_name;
    }

    // The never return type indicates that the method never returns a value; this method throws an error every time it is invoked
    public neverReturn():never{
        throw new EmptyError;
    }

    public returnANumber():number{
        return 100;
    }
}

/**
 * And, yes, inheritance is supported.
 */

 export class VeganRecipe extends Recipe{

 }

 /**
  * Interfaces are supported as well.
  */
 export interface Edible{

 }