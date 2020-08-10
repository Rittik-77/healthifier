import { WorkoutService } from './../services/workout/workout.service';
import { WorkoutTrackerService } from './../services/workout-tracker/workout-tracker.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Workout } from '../models/workout/workout';
import { Validators, FormGroup, FormControl } from '@angular/forms';
import { WorkoutTracker } from '../models/workout-tracker/workout-tracker';

@Component({
  selector: 'app-workout-tracker-edit-form',
  templateUrl: './workout-tracker-edit-form.component.html',
  styleUrls: ['./workout-tracker-edit-form.component.css']
})
export class WorkoutTrackerEditFormComponent implements OnInit {

  @Input() currentWorkoutTrackerName: string
  @Input() currentWorkoutTrackerDuration: number
  @Input() currentWorkoutTrackerDate: string
  @Input() currentWorkoutTrackerId: number
  @Output() close = new EventEmitter()

  workoutList: Workout[]
  today = new Date().toISOString().split("T")[0]

  constructor(
    private workoutTrackerService: WorkoutTrackerService,
    private workoutService: WorkoutService
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
    
    this.workoutTrackerForm.get('workoutName').setValue(this.currentWorkoutTrackerName)
    this.workoutTrackerForm.get('duration').setValue(this.currentWorkoutTrackerDuration)
    this.workoutTrackerForm.get('date').setValue(this.currentWorkoutTrackerDate)
  }

  closeForm() {
    this.close.emit({'closeEditForm': true});
  }

  // On submit button
  onSubmitUpdateToTracker() {
    let updatedWorkoutTracker = new WorkoutTracker(this.date.value, this.workoutName.value, this.duration.value)
    updatedWorkoutTracker.id = this.currentWorkoutTrackerId

    this.workoutTrackerService.updateWorkoutToTracker(this.currentWorkoutTrackerId, updatedWorkoutTracker)
      .subscribe(response => {
        if (response as boolean) {
          this.close.emit(
            {
              'closeEditForm': true,
              'editResponse': true
            }
          )
        }
      }, error => console.log(error)
      )
  }
}