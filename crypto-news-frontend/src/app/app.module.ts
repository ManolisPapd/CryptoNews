import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { CryptosComponent } from './cryptos/cryptos.component';
import {Router, RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import { CryptoComponent } from './cryptos/crypto/crypto.component';
import {FormsModule} from "@angular/forms";
import { PortfolioComponent } from './portfolio/portfolio.component';
import { ContactComponent } from './contact/contact.component';
import { LoginComponent } from './login/login.component';


const appRoutes :Routes=[
  {
    path:'',
    component:CryptosComponent,

  },
  {
    path:'portfolio',
    component:PortfolioComponent,
  },
  {
    path:'contact',
    component:ContactComponent
  },
  {
    path:'**',
    component:CryptosComponent
  },

]

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    CryptosComponent,
    CryptoComponent,
    PortfolioComponent,
    ContactComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes, {enableTracing:true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
