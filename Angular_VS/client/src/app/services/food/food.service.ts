import { HttpClient } from '@angular/common/http';
import { BaseHttpService } from './../http/base-http.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as Constants from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class FoodService extends BaseHttpService {

  baseUrl = Constants.API + 'foods/'

  constructor(protected http: HttpClient) {
    super(http)
  }

  // Get all foods
  getAllFoods(): Observable<any> {
    return this.get(this.baseUrl)
  }

  // Get Food By Name
  getFoodByName(foodName: string): Observable<any> {
    const url = this.baseUrl + foodName
    return this.get(url)
  }
}
