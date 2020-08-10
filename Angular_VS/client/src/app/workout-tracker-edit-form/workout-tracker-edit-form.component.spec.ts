import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutTrackerEditFormComponent } from './workout-tracker-edit-form.component';

describe('WorkoutTrackerEditFormComponent', () => {
  let component: WorkoutTrackerEditFormComponent;
  let fixture: ComponentFixture<WorkoutTrackerEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutTrackerEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutTrackerEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
