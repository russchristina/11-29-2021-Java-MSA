import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateSearchComponent } from './associate-search.component';

describe('AssociateSearchComponent', () => {
  let component: AssociateSearchComponent;
  let fixture: ComponentFixture<AssociateSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssociateSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
