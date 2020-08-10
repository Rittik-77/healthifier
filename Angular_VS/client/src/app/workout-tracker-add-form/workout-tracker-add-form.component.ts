import { Workout } from './../models/workout/workout';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { WorkoutTrackerService } from '../services/workout-tracker/workout-tracker.service';
import { WorkoutService } from '../services/workout/workout.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { WorkoutTracker } from '../models/workout-tracker/workout-tracker';

@Component({
  selector: 'app-workout-tracker-add-form',
  templateUrl: './workout-tracker-add-form.component.html',
  styleUrls: ['./workout-tracker-add-form.component.css']
})
export class WorkoutTrackerAddFormComponent implements OnInit {

  @Output() close = new EventEmitter()
  workoutList: Workout[]
  today = new Date().toISOString().split("T")[0]

  constructor(
    private workoutService: WorkoutService,
    private workoutTrackerService: WorkoutTrackerService
  ) { }

  workoutTrackerForm = new FormGroup({
    workoutName: new FormControl('', Validators.required),
    duration: new FormControl('', [Validators.required, Validators.pattern('[0-9]+')]),
    date: new FormControl('', Validators.required)
  })

  get workoutName() {
    return this.workoutTrackerForm.get('workoutName')
  }

  get duration() {
    return this.workoutTrackerForm.get('duration')
  }

  get date() {
    return this.workoutTrackerForm.get('date')
  }

  ngOnInit() {
    this.workoutService.getAllWorkouts()
      .subscribe(response => {
        this.workoutList = response
      }, error => console.log(error))
  }

  closeForm() {
    this.close.emit({'closeAddForm': true})
  }

  // On submit button
  onSubmitAddToTracker() {
    let workoutTrackerVO = new WorkoutTracker(this.date.value, this.workoutName.value, this.duration.value)
    this.workoutTrackerService.addWorkoutToTracker(workoutTrackerVO)
      .subscribe(response => {
        if (response as boolean) {
          this.close.emit(
            {
              'closeAddForm': true,
              'addResponse': true
            });
        }
      }, error => console.log(error))
  }

}
