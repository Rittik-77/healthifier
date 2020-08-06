import { HttpClient } from '@angular/common/http';
import { TokenHttpService } from './../http/token-http.service';
import { Injectable } from '@angular/core';
import * as Constants from '../../app.constants';
import { Observable } from 'rxjs';
import { WorkoutTracker } from 'src/app/models/workout-tracker/workout-tracker';

@Injectable({
  providedIn: 'root'
})
export class WorkoutTrackerService extends TokenHttpService {

  baseUrl = Constants.API + 'workoutTrackers/'

  constructor(protected http: HttpClient) {
    super(http)
  }

  // Get All
  getWorkoutTracker(): Observable<any> {
    return this.get(this.baseUrl)
  }

  // Add
  addWorkoutToTracker(payload: WorkoutTracker): Observable<any> {
    return this.post(this.baseUrl, payload)
  }

  // Update
  updateWorkoutToTracker(id: number, payload: WorkoutTracker): Observable<any> {
    const url = this.baseUrl + id
    return this.put(url, payload)
  }

  // Delete
  deleteWorkoutFromTracker(id: number): Observable<any> {
    const url = this.baseUrl + id
    return this.delete(url)
  }
}
