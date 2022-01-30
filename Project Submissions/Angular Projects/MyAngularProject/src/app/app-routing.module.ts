import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchedulerComponent } from './scheduler/scheduler.component';
import { TimeOffComponent} from './time-off/time-off.component';



import { DashboardComponent } from './dashboard/dashboard.component';
import { AssociatesComponent } from './associates/associates.component';
import { AssociateDetailComponent } from './associate-detail/associate-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: AssociateDetailComponent, },
  { path: 'associates', component: AssociatesComponent },
  { path: 'scheduler', component: SchedulerComponent },
  { path: 'time-off', component: TimeOffComponent },


  
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}