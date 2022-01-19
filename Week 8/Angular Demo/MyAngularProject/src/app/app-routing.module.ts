import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import {RecipesComponent} from './components/recipes/recipes.component'

const routes: Routes = [
  {
    component: HomeComponent,
    path: ''
  },
  {
    component: RecipesComponent,
    path: 'recipes'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
