import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutTrackerAddFormComponent } from './workout-tracker-add-form.component';

describe('WorkoutTrackerAddFormComponent', () => {
  let component: WorkoutTrackerAddFormComponent;
  let fixture: ComponentFixture<WorkoutTrackerAddFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutTrackerAddFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutTrackerAddFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
