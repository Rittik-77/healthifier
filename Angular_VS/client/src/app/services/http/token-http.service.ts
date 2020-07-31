import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenHttpService {

  private defaultHeaders: HttpHeaders

  constructor(protected http: HttpClient) { }

  // GET Method
  get(url: string): Observable<any> {
    this.defaultHeaders = new HttpHeaders({
      'Content-Type':  'application/json',
      'token': localStorage.getItem('token')
    })
    return this.http.get(url, { headers: this.defaultHeaders });
  }

  // POST Method
  post(url: string, body: Object): Observable<any> {
    this.defaultHeaders = new HttpHeaders({
      'Content-Type':  'application/json',
      'token': localStorage.getItem('token')
    })
    return this.http.post(url, body, { headers: this.defaultHeaders });
  }

  // PUT Method
  put(url: string, body: Object): Observable<any> {
    this.defaultHeaders = new HttpHeaders({
      'Content-Type':  'application/json',
      'token': localStorage.getItem('token')
    })
    return this.http.put(url, body, { headers: this.defaultHeaders });
  }

  // DELETE Method
  delete(url: string): Observable<any> {
    this.defaultHeaders = new HttpHeaders({
      'Content-Type':  'application/json',
      'token': localStorage.getItem('token')
    })
    return this.http.delete(url, { headers: this.defaultHeaders });
  }
}
