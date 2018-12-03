import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {User} from "../cryptos/model/user";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private validateFun:AuthenticationService) {

  }




  ngOnInit() {

    var user:User = new class implements User {
      password: string;
      username: string;
    };

    user.username ="love";
    user.password ="12345";

    this.validateFun.validateUser(user).subscribe(
      output=>{
        console.log(output);

      }

    );
  }

}
