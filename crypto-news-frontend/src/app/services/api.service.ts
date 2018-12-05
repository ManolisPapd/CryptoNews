import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Currency} from "../cryptos/model/currency";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private ALL_CRYPTO_FROM_COINCAP = "https://api.coincap.io/v2/assets";
  private BASE_URL = "http://localhost:8082";
  private ALL_CURRENCIES_BY_USER = `${this.BASE_URL}\\portfolio\\currencies\\`;
  private ADD_CURRENCY = `${this.BASE_URL}\\portfolio\\currency\\`;


  constructor(private http: HttpClient) {

  }


  getAllCryptos() : Observable<Crypto[]>{
    return this.http.get<Crypto[]>(this.ALL_CRYPTO_FROM_COINCAP);
  }

  authorizeUser(username:string,token:string){

    let header = new HttpHeaders();
    let other_header = header.append('Authorization',token);
    console.log(other_header.get('Authorization'))

    return this.http.get(this.BASE_URL +"\\",{headers:other_header});
  }

  getPortfolio(username:string,token:string): Observable<Currency[]>{

    let header = new HttpHeaders();
    let other_header = header.append('Authorization',token);


    return this.http.get(this.ALL_CURRENCIES_BY_USER +username,{headers:other_header});
  }

  addCurrency(currency:Currency,token:string){

    var reqHeader = new HttpHeaders({ 'Content-Type': 'application/json','No-Auth':'True' });

    return this.http.post(this.ADD_CURRENCY,currency,{
      headers: new HttpHeaders().set('Authorization',token)
      .set('Content-Type', 'application/json')
  });

  }
}
