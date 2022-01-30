import { Component, OnInit } from '@angular/core';

import { Associate } from '../associate';
import { AssociateService } from '../associate.service';

@Component({
  selector: 'app-associates',
  templateUrl: './associates.component.html',
  styleUrls: ['./associates.component.css']
})
export class AssociatesComponent implements OnInit {
  associates: Associate[] = [];

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.getAssociates();
  }

  getAssociates(): void {
    this.associateService.getAssociates()
    .subscribe(associates => this.associates = associates);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.associateService.addAssociate({ name } as Associate)
      .subscribe(associate => {
        this.associates.push(associate);
      });
  }

  delete(associate: Associate): void {
    this.associates = this.associates.filter(h => h !== associate);
    this.associateService.deleteAssociate(associate.id).subscribe();
  }
}