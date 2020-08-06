import { TestBed } from '@angular/core/testing';

import { WorkoutTrackerService } from './workout-tracker.service';

describe('WorkoutTrackerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkoutTrackerService = TestBed.get(WorkoutTrackerService);
    expect(service).toBeTruthy();
  });
});
