import { Component, OnInit } from '@angular/core';
import {ApiService} from "../shared/api.service";
import {Crypto} from "./model/crypto";
import { interval } from 'rxjs';


@Component({
  selector: 'app-cryptos',
  templateUrl: './cryptos.component.html',
  styleUrls: ['./cryptos.component.css']
})
export class CryptosComponent implements OnInit {
  cryptos: Crypto[];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    //Possible API call limit
    // interval(500).subscribe(
    //   x => this.getAllCryptos()
    // )

    this.getAllCryptos()

  }

  public getAllCryptos(){
    this.apiService.getAllCryptos().subscribe(
      output =>{
        let res = output['data'];

        this.cryptos = res;

        console.log(this.cryptos);
      },
      err=>{
        alert("Unable to retrieve data");
      }
    );
  }

}
