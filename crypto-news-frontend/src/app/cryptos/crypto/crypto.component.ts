import {Component, Input, OnInit} from '@angular/core';
import {Crypto} from "../model/crypto";

@Component({
  selector: 'app-crypto',
  templateUrl: './crypto.component.html',
  styleUrls: ['./crypto.component.css']
})
export class CryptoComponent implements OnInit {
  @Input() objectCrypto: Crypto;

  constructor() { }

  ngOnInit() {

  }


  updateColor24Prices(){
    var price24hr = parseFloat(this.objectCrypto.changePercent24Hr);
    var element = "price24hr_" + this.objectCrypto.id;
    if(price24hr < 0 ){
      document.getElementById(element).style.color = "#ff0000";
    }
    else{
      document.getElementById(element).style.color = "#0cff00";
    }
  }

}
