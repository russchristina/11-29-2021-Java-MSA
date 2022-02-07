import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TimeOff } from '../time-off';
import { TimeoffService } from '../timeoff.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-timeoff-details',
  templateUrl: './timeoff-details.component.html',
  styleUrls: ['./timeoff-details.component.css']
})
export class TimeoffDetailsComponent implements OnInit {
  timeoff: TimeOff | undefined;

  constructor(
    private route: ActivatedRoute,
    private timeoffService: TimeoffService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getTimeOff();
  }

  getTimeOff(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.timeoffService.getTimeOff(id)
      .subscribe(timeoff => this.timeoff = timeoff);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.timeoff) {
      this.timeoffService.updateTimeOff(this.timeoff)
        .subscribe(() => this.goBack());
    }
  }
}