import {Component, Input, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {Currency} from "../../cryptos/model/currency";
import {ApiService} from "../../services/api.service";
import {FormControl, FormGroup} from "@angular/forms";
import {User} from "../../cryptos/model/user";
import {Crypto} from "../../cryptos/model/crypto";


@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {
  currencies: Currency[];
  username:string;
  cryptos: Crypto[];

  currencyForm:FormGroup;

  constructor(private router:Router,private authService:AuthenticationService,private apiService:ApiService) { }

  ngOnInit() {

    //If there is none logged in, redirect to login page
    if(!this.authService.getToken()){
      this.router.navigate(['login']);
    }
    else{
      //Get the current user that is log in
      this.username = this.authService.getUsername();
      this.getPortfolio();

      //Init the form values
      this.currencyForm = new FormGroup(
        {
          currency: new FormControl(),
          dateBought: new FormControl(),
          priceBought: new FormControl()
        }
      )

      //Get cryptos to add the name to the form
      this.apiService.getAllCryptos().subscribe(
        output=>{
          let res = output['data'];

          this.cryptos = res;
        }
      )
    }
  }


  public getPortfolio(){

    this.apiService.getAllCryptos().subscribe(
      output=>{
        let res = output['data'];

        this.cryptos = res;

        //Send username and the token and we are getting back the portfolio
        this.apiService.getPortfolio(this.authService.getUsername(),this.authService.getToken()).subscribe(
          output=>{
            this.currencies = output;
            var i:number;
            for(i=0;i<this.currencies.length;i++){
              var difference:number;
              var rate:number;

              var j:number;
              for(j=0;j<this.cryptos.length;j++){
                if(this.currencies[i].currency == this.cryptos[j].id){
                  difference = parseFloat(this.cryptos[j].priceUsd) - parseFloat(this.currencies[i].priceBought);
                  rate = (difference * 100) / parseFloat(this.currencies[i].priceBought);
                  this.currencies[i].rate = rate.toFixed(2).toString();
                }
              }

              //console.log(this.cryptos[0]);


            }

          },
          err=>{
            alert("Unable to retrieve portfolio!");
          }

        );
      }
    );





  }

  public addCurrency(data){
    var currency:Currency = new class implements Currency {
      id:number;
      currency:string;
      dateBought:string;
      priceBought:string;
      username:string;
      rate:string;
    };

    currency.currency = data.currency;
    currency.priceBought = data.priceBought;
    currency.dateBought = data.dateBought;

    var i:number;
    var flag:boolean;

    for(i=0;i<this.cryptos.length;i++){
      if(currency.currency.toUpperCase() == this.cryptos[i].name.toUpperCase()
        || currency.currency.toUpperCase() == this.cryptos[i].symbol.toUpperCase()
        || currency.currency.toUpperCase() == this.cryptos[i].id.toUpperCase()){

        //Get the id value to store in database
        currency.currency = this.cryptos[i].id;

        flag = true;
      }

    }

    if(!flag){
      alert("Currency not found, check your spelling!");
    }
    else{
      //TODO add new currency to database
      currency.username = this.authService.getUsername();
      this.apiService.addCurrency(currency,this.authService.getToken()).subscribe(
        output=>{
          this.router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
            this.router.navigate(["portfolio"]));
        },
        err=>{
          alert("Unable to add new currency");
        }
      );

    }


  }

  public logout(){
    this.authService.removeToken();
    this.router.navigate(['']);
  }



}
