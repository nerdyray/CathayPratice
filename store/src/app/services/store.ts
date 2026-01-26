import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root',
})
export class Store {
  constructor(private http: HttpClient) { }
  headers = new HttpHeaders({
    'Content-Type': 'application'
  })
}
