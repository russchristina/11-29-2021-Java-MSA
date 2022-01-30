import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService } from './in-memory-data.service';
import { AssociatesComponent } from './associates/associates.component';
import { AssociateDetailComponent } from './associate-detail/associate-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AssociateSearchComponent } from './associate-search/associate-search.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { SchedulerComponent } from './scheduler/scheduler.component';
import { ShiftTradeComponent } from './shift-trade/shift-trade.component';
import { TimeOffComponent } from './time-off/time-off.component';


@NgModule({
  declarations: [
    AppComponent,
   
    
    AssociatesComponent,
    AssociateDetailComponent,
    MessagesComponent,
    DashboardComponent,
    AssociateSearchComponent,
    ScheduleComponent,
    SchedulerComponent,
    ShiftTradeComponent,
    TimeOffComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,

// The HttpClientInMemoryWebApiModule module intercepts HTTP requests
// and returns simulated server responses.
// Remove it when a real server is ready to receive requests.
HttpClientInMemoryWebApiModule.forRoot(
  InMemoryDataService, { dataEncapsulation: false }
)
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
