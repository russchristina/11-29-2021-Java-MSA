import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Associate } from '../associate';
import { AssociateService } from '../associate.service';

@Component({
  selector: 'app-associate-detail',
  templateUrl: './associate-detail.component.html',
  styleUrls: [ './associate-detail.component.css' ]
})
export class AssociateDetailComponent implements OnInit {
 
  associate: Associate | undefined;

  constructor(
    private route: ActivatedRoute,
    private associateService: AssociateService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getAssociate();
  }

  getAssociate(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.associateService.getAssociate(id)
      .subscribe(associate => this.associate = associate);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.associate) {
      this.associateService.updateAssociate(this.associate)
        .subscribe(() => this.goBack());
    }
  }
}
