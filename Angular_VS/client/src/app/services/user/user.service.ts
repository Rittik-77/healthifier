import { AuthService } from './../auth/auth.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenHttpService } from '../http/token-http.service';
import * as Constants from '../../app.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService extends TokenHttpService {

  constructor(protected http: HttpClient) {
    super(http);
  }

  // Get username for logged in user
  getLoggedUsername(): Observable<any> {
    const url = Constants.AUTH + 'loggedUsername'
    return this.get(url)
  }
}
