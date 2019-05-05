import { TestBed, inject } from '@angular/core/testing';

import { DiscService } from './disc.service';

describe('DiscServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiscService]
    });
  });

  it('should be created', inject([DiscService], (service: DiscService) => {
    expect(service).toBeTruthy();
  }));
});
