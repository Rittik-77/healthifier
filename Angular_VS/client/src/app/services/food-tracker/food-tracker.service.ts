import { FoodTracker } from './../../models/food-tracker/food-tracker';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenHttpService } from '../http/token-http.service';
import { Observable } from 'rxjs';
import * as Constants from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class FoodTrackerService extends TokenHttpService {

  baseUrl = Constants.API + 'foodTrackers';

  constructor(protected http: HttpClient) {
    super(http)
  }

  // Get Food Tracker
  getFoodTracker(): Observable<any> {
    return this.get(this.baseUrl)
  }

  // Add food to tracker
  addFoodToTracker(payload: FoodTracker): Observable<any> {
    return this.post(this.baseUrl, payload)
  }
}
