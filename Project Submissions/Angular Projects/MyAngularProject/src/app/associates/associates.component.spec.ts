import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociatesComponent } from './associates.component';

/*



*/


describe('AssociateComponent Tests', () => {
  let component: AssociatesComponent;
  let fixture: ComponentFixture<AssociatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssociatesComponent ]

    })
    .compileComponents();
  });
//
  beforeEach(() => {
    fixture = TestBed.createComponent(AssociatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //"it" function veriies test and defines test logic
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
