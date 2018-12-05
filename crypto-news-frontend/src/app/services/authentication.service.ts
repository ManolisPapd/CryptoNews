import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../cryptos/model/user";
import { map, filter, catchError, mergeMap } from 'rxjs/operators';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public apiURL:string="http://localhost:8082";

  constructor(private httpClient:HttpClient) { }

  validateUser(user:User){


    var reqHeader = new HttpHeaders({ 'Content-Type': 'application/json','No-Auth':'True' });

    return this.httpClient.post(this.apiURL + '/login', user, {headers:reqHeader, responseType: 'text'});
  }

  storeToken(token: string) {
    localStorage.setItem("token", token);
  }
  getToken() {
    return localStorage.getItem("token");
  }
  removeToken() {
    return localStorage.removeItem("token");
  }

  storeUsername(username:string){
    localStorage.setItem("username", username);
  }

  getUsername() {
    return localStorage.getItem("username");
  }
}
