import { User } from './../../models/user/user';
import { HttpClient } from '@angular/common/http';
import { BaseHttpService } from './../http/base-http.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as Constants from '../../app.constants'

@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseHttpService {

  constructor(protected http: HttpClient) { 
    super(http)
  }

  // Register
  register(payload: User): Observable<any> {
    const url = Constants.AUTH + 'register'
    return this.post(url, payload)
  }

  // Login
  login(payload: User): Observable<any> {
    const url = Constants.AUTH + 'login'
    return this.post(url, payload)
  }

}
