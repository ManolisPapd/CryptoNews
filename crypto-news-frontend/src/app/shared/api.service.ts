import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private ALL_CRYPTO_FROM_COINCAP = "https://api.coincap.io/v2/assets";

  constructor(private http: HttpClient) {

  }


  getAllCryptos() : Observable<Crypto[]>{
    return this.http.get<Crypto[]>(this.ALL_CRYPTO_FROM_COINCAP);

  }
}
