import { TestBed } from '@angular/core/testing';

import { ShifttradeService } from './shifttrade.service';

describe('ShifttradeService', () => {
  let service: ShifttradeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShifttradeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
