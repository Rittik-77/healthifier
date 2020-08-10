import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenHttpService } from '../http/token-http.service';
import * as Constants from '../../app.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SummaryService extends TokenHttpService {

  baseUrl = Constants.API + 'summary'

  constructor(protected http: HttpClient) {
    super(http)
  }

  // Get Summary
  getSummary(): Observable<any> {
    return this.get(this.baseUrl)
  }

}
