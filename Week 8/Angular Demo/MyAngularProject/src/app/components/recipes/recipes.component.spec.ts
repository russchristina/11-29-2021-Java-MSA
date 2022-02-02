import { ComponentFixture, TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing'
import { RecipesComponent } from './recipes.component';
import { RecipeService } from 'src/app/services/recipe.service';
import {Observable, of} from 'rxjs';
import { Recipe } from 'src/app/models/recipe';

/**
 * We wish to mock our RecipeService as the RecipeService Class's findAllRecipes method no longer return an observable containing recipes. In order to mock the RecipeService, we can simply extend it and override its methods:
 */

 export class MockRecipeService extends RecipeService{

  findAllRecipes():Observable<Recipe[]>{
    
    //This method return what we refer to as a "stub". A stub is canned answer that is returned every single time we invoke this method; it is not dynamic as that is not the point of stubbing.
    let mockRecipes:Observable<Recipe[]> = of([new Recipe(1, 'cake', 'delicous sugar bread', '', [], 'christina'), new Recipe(2, 'pancakes', 'also delicious sugar bread but with not as much sugar', '', [], 'christina')]);
    return mockRecipes;
  }
 }

/**
 * In order to test our Angular components, we will be using a JS testing framework known as "Jasmine". Jasmine follows a "BDD" style of testing. BDD stands for "Behavior Driven Development". BDD emphasizes specifying what is being is being tested at a high level (e.g. "in plain English").
 * 
 * Like in JUnit, we can perform basic "setup" and "teardown" with several built-in functions (e.g. beforeEach). Generally speaking, we group a bunch of tests and their shared setup in a "suite". A "suite" is denoted by the "describe" function. The function takes a "description" of the entire suite and a callback function which defines all of the setup, teardown, and actual tests.
 */
describe('RecipesComponent Tests', () => {
  //Object under test
  let component: RecipesComponent;
  let fixture: ComponentFixture<RecipesComponent>;

  //Setup: This particular setup creates a mock App Module that is as minimal as it can be; only components and services that are actually needed to run the tests will be declared here.
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipesComponent ],
      //We'll be using the mock HttpClientModule in order to guarantee that none of these tests cause an actual HTTP request to be made.
      imports: [HttpClientTestingModule],
      //We can tell Angular that we wish to use the MockRecipeService wherever it would usually use the actual RecipeService.
      providers: [{provide: RecipeService, useClass: MockRecipeService}]
    })
    .compileComponents();
  });

  // Here we create an instance of our RecipesComponent using the TestBed as it was configured above.
  beforeEach(() => {
    fixture = TestBed.createComponent(RecipesComponent);
    component = fixture.componentInstance;
    // For unit tests, Angular does not automatically detect changes to component state. As a result, we will often need to call detectChanges().
    // fixture.detectChanges(); COMMENTED OUT FOR YOUR REFERENCE AS THE SECOND TEST REALLY DOES FUNCTION DIFFERENTLY DEPENDING ON WHETHER OR NOT YOU CALL DETECT CHANGES.
  });

  // The "it" function denotes a test. Note that this function takes a description and a callback function which defines the test logic.
  it('should create', () => {
    //Rather than "assertions", we have "expectations" for our Jasmine tests. 
    expect(component).toBeTruthy();
  });

  // Let's test that the size of the recipe [] actually increases appropriately when findAllRecipes is called:
  it('should increase the size of the recipe array when findAllRecipes is called', () => {
    expect(component.recipes.length).toBe(0);
    component.findAllRecipes();
    expect(component.recipes.length).toBeGreaterThan(0);
  });
});
