import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {User} from "../cryptos/model/user";
import {AuthenticationService} from "../services/authentication.service";
import {Usermodel} from "../cryptos/model/usermodel";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../services/api.service";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
   loginForm: FormGroup;


  constructor(private router:Router,private authService:AuthenticationService,private apiService:ApiService) {

  }




  ngOnInit() {
    this.loginForm = new FormGroup(
      {
        username: new FormControl(),
        password: new FormControl()
      }
    )
  }

  login(data){
    var user:User = new class implements User {
      password: string;
      username: string;
    };

    user.username = data.username;
    user.password = data.password;


    this.authService.validateUser(user).subscribe(
      token=>{
        this.authService.storeToken(token);
        //Authorize sto susthma
        this.apiService.authorizeUser(user.username,this.authService.getToken()).subscribe(
          output =>{
            this.authService.storeUsername(user.username);
            this.router.navigate(['portfolio']);

          }
        )

      },
      error =>{
        console.log(error.message);
      }

    );

  }


}
