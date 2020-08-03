import { TestBed } from '@angular/core/testing';

import { FoodTrackerService } from './food-tracker.service';

describe('FoodTrackerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FoodTrackerService = TestBed.get(FoodTrackerService);
    expect(service).toBeTruthy();
  });
});
