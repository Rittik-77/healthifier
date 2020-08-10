import { HttpClient } from '@angular/common/http';
import { BaseHttpService } from './../http/base-http.service';
import { Injectable } from '@angular/core';
import * as Constants from '../../app.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService extends BaseHttpService {

  baseUrl = Constants.API + 'workouts/'

  constructor(protected http: HttpClient) {
    super(http)
  }

  // Get all Workouts
  getAllWorkouts(): Observable<any> {
    return this.get(this.baseUrl)
  }

}
