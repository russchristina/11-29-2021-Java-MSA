import { Component, OnInit } from '@angular/core';
import { TimeOff } from '../time-off';
import { TimeoffService } from '../timeoff.service';
@Component({
  selector: 'app-time-off',
  templateUrl: './time-off.component.html',
  styleUrls: ['./time-off.component.css']
})
export class TimeOffComponent implements OnInit {
  timeoffs: TimeOff[] = [];

  constructor(private timeoffService: TimeoffService) { }

  ngOnInit(): void {
    this.getTimeOffs();
  }

  getTimeOffs(): void {
    this.timeoffService.getTimeOffs()
    .subscribe(timeoffs => this.timeoffs = timeoffs);
  }

  add(sender: string): void {
    sender = sender.trim();
    if (!sender) { return; }
    this.timeoffService.addTimeOff({ sender } as TimeOff)
      .subscribe(timeoff => {
        this.timeoffs.push(timeoff);
      });
  }

  delete(timeoff: TimeOff): void {
    this.timeoffs = this.timeoffs.filter(u => u !== timeoff);
    this.timeoffService.deleteTimeOff(timeoff.id).subscribe();
  }
}