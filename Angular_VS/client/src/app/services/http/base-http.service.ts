import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BaseHttpService {

  private defaultHeaders: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(protected http: HttpClient) { }

  // Standard GET Method
  get(url: string): Observable<any> {
    return this.http.get(url);
  }

  // Standard POST Method
  post(url: string, body: Object): Observable<any> {
    return this.http.post(url, body, { headers: this.defaultHeaders });
  }

  // Standard PUT Method
  put(url: string, body: Object): Observable<any> {
    return this.http.put(url, body, { headers: this.defaultHeaders });
  }

  // Standard DELETE Method
  delete(url: string): Observable<any> {
    return this.http.delete(url);
  }
}
