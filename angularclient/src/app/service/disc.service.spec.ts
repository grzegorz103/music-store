import { TestBed, inject } from '@angular/core/testing';

import { DiscServiceService } from './disc-service.service';

describe('DiscServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiscServiceService]
    });
  });

  it('should be created', inject([DiscServiceService], (service: DiscServiceService) => {
    expect(service).toBeTruthy();
  }));
});
