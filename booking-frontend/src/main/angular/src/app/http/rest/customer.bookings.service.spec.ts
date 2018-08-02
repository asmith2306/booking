import { TestBed, inject } from '@angular/core/testing';

import { CustomerBookingsService } from './customer.bookings.service';

describe('CustomerBookingsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CustomerBookingsService]
    });
  });

  it('should be created', inject([CustomerBookingsService], (service: CustomerBookingsService) => {
    expect(service).toBeTruthy();
  }));
});
