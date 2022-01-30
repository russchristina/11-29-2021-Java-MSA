import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShiftTradeComponent } from './shift-trade.component';

describe('ShiftTradeComponent', () => {
  let component: ShiftTradeComponent;
  let fixture: ComponentFixture<ShiftTradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShiftTradeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShiftTradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
