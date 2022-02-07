import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimeoffDetailsComponent } from './timeoff-details.component';

describe('TimeoffDetailsComponent', () => {
  let component: TimeoffDetailsComponent;
  let fixture: ComponentFixture<TimeoffDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TimeoffDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TimeoffDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
