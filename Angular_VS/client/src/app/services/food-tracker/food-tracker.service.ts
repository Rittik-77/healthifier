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

  baseUrl = Constants.API + 'foodTrackers/';

  constructor(protected http: HttpClient) {
    super(http)
  }

  // Get All
  getFoodTracker(): Observable<any> {
    return this.get(this.baseUrl)
  }

  // Add
  addFoodToTracker(payload: FoodTracker): Observable<any> {
    return this.post(this.baseUrl, payload)
  }

  // Update
  updateFoodToTracker(id: number, payload: FoodTracker): Observable<any> {
    const url = this.baseUrl + id
    return this.put(url, payload)
  }

  // Delete
  deleteFoodFromTracker(id: number): Observable<any> {
    const url = this.baseUrl + id
    return this.delete(url)
  }
}
