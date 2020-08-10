import { Component, OnInit } from '@angular/core';
import { WorkoutTracker } from '../models/workout-tracker/workout-tracker';
import { WorkoutTrackerService } from '../services/workout-tracker/workout-tracker.service';

@Component({
  selector: 'app-workout-tracker',
  templateUrl: './workout-tracker.component.html',
  styleUrls: ['./workout-tracker.component.css']
})
export class WorkoutTrackerComponent implements OnInit {

  workoutTrackerList: WorkoutTracker[]
  listExist: boolean
  uiMessage: string

  enableAddForm: boolean
  enableEditForm: boolean

  // Edit_Workout_Tracker Form properties
  editFormWorkoutName: string
  editFormDuration: number
  editFormDate: string
  editFormId: number

  constructor(private workoutTrackerService: WorkoutTrackerService) { }

  ngOnInit() {

    // Close all forms
    this.enableAddForm = false
    this.enableEditForm = false

    // Fetch all workouts for logged user
    this.workoutTrackerService.getWorkoutTracker()
      .subscribe(response => {
        this.workoutTrackerList = response
        this.listExist = true
      }, error => {
        if (error.error != null && error.error.exceptionLevel === 'INFO') {
          this.listExist = false
          this.uiMessage = error.error.uiMessage
        }
      })
  }

  onAdd() {
    this.enableEditForm = false
    this.enableAddForm = true
  }

  closeAddForm(eventObject: any) {
    // validate if form needs to be closed
    if (eventObject['closeAddForm']) {
      this.enableAddForm = false
    }

    // validate if page needs to be refreshed
    if (eventObject['addResponse']) {
      this.ngOnInit()
    }
  }

  closeEditForm(eventObject: any) {
    // validate if form needs to be closed
    if (eventObject['closeEditForm']) {
      this.enableEditForm = false
    }

    // validate if page needs to be refreshed
    if (eventObject['editResponse']) {
      this.ngOnInit()
    }
  }

  onEdit(workoutTracker: WorkoutTracker) {
    // close add form
    this.enableAddForm = false

    // set variables to sent to edit workout tracker component
    this.editFormId = workoutTracker.id
    this.editFormWorkoutName = workoutTracker.workoutName
    this.editFormDuration = workoutTracker.duration

    // calculate date wrt IST
    let date = new Date(workoutTracker.date)
    date.setMinutes(date.getMinutes() - date.getTimezoneOffset())
    this.editFormDate = date.toISOString().split("T")[0]

    // enable edit form
    this.enableEditForm = true
  }

  onDelete(workoutTracker: WorkoutTracker) {
    // Call API and refresh if successful
    this.workoutTrackerService.deleteWorkoutFromTracker(workoutTracker.id)
      .subscribe(response => {
        if (response as boolean)
          this.ngOnInit()
      }, error => console.error())
  }
}
