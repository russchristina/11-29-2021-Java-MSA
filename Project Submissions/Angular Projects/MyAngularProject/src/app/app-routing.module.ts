import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchedulerComponent } from './scheduler/scheduler.component';
import { TimeOffComponent} from './time-off/time-off.component';
import { TimeoffDetailsComponent} from './timeoff-details/timeoff-details.component';




import { DashboardComponent } from './dashboard/dashboard.component';
import { AssociatesComponent } from './associates/associates.component';
import { AssociateDetailComponent } from './associate-detail/associate-detail.component';
import { ShiftTradeComponent } from './shift-trade/shift-trade.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
 
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: AssociateDetailComponent, },
  { path: 'details/:id', component: TimeoffDetailsComponent, },

  { path: 'associates', component: AssociatesComponent },
  { path: 'scheduler', component: SchedulerComponent },
  { path: 'time-off', component: TimeOffComponent },
  { path: 'shift-trade', component: ShiftTradeComponent },


  
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}