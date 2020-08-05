import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodTrackerEditFormComponent } from './food-tracker-edit-form.component';

describe('FoodTrackerEditFormComponent', () => {
  let component: FoodTrackerEditFormComponent;
  let fixture: ComponentFixture<FoodTrackerEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FoodTrackerEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FoodTrackerEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
