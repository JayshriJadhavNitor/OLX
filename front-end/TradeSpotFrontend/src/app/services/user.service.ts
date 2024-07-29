import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { user } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  
  baseURL = "http://localhost:8080/user";

  getBuyerCount() {
    return this.http.get<number>(this.baseURL + '/getBuyerCount');
  }

  getSellerCount() {
    return this.http.get<number>(this.baseURL + '/getSellerCount');
  }

  getRecentUser() {
    return this.http.get<user[]>(this.baseURL + "/recentUser");
  }
}
