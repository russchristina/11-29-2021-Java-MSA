import { TestBed } from '@angular/core/testing';

import { TimeoffService } from './timeoff.service';

describe('TimeoffService', () => {
  let service: TimeoffService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TimeoffService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
