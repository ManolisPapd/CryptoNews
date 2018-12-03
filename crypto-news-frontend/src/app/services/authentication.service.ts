import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../cryptos/model/user";
import { map, filter, catchError, mergeMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public apiURL:string="http://localhost:8082";

  constructor(private httpClient:HttpClient) { }

  validateUser(user:User){
    // const headers = new HttpHeaders({
    //   'Content-Type': 'application/x-www-form-urlencoded'
    // });
    //
    // const data = new FormData();
    // data.append("username", "love");
    // data.append("password", "12345");

    let userData = "username=love"+ "&password=12345" + "&grant_type=password";
    let reqHeader = new HttpHeaders({ 'Content-Type': 'application/javascript','No-Auth':'True' });


    return this.httpClient.post<User>(this.apiURL + '/login',user,{headers:reqHeader});
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
}
