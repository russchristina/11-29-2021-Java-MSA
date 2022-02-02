import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeCardComponent } from './recipe-card.component';
import { Recipe } from 'src/app/models/recipe';

describe('RecipeCardComponent', () => {
  let component: RecipeCardComponent;
  let fixture: ComponentFixture<RecipeCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipeCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeCardComponent);
    component = fixture.componentInstance;
    //Cheat way of dealing with the fact that no @Input will be passed down for this test because an instance of the parent component doens't exist to pass down the state that is needed.
    component.recipe = new Recipe(1, 'stuff', '', '', [], '')
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should toggle the isHidden property between true and false when toggleVisiblity() is called', () => {
    expect(component.isHidden).toBeTrue()
    component.toggleVisibility()
    expect(component.isHidden).toBeFalse()
  });
});
