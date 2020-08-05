import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodTrackerAddFormComponent } from './food-tracker-add-form.component';

describe('FoodTrackerAddFormComponent', () => {
  let component: FoodTrackerAddFormComponent;
  let fixture: ComponentFixture<FoodTrackerAddFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FoodTrackerAddFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FoodTrackerAddFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
