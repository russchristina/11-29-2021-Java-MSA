import { Component, OnInit } from '@angular/core';
import { Associate } from '../associate';
import { AssociateService } from '../associate.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  
  associates: Associate[]=[];

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.getAssociates();
  }

  getAssociates(): void {
    this.associateService.getAssociates()
      .subscribe(associates => this.associates = associates.slice(1, 5));
  }
}