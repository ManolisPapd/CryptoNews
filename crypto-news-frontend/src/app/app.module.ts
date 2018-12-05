import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { CryptosComponent } from './cryptos/cryptos.component';
import {Router, RouterModule, Routes, ActivatedRoute} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import { CryptoComponent } from './cryptos/crypto/crypto.component';
import {FormsModule} from "@angular/forms";
import { PortfolioComponent } from './login/portfolio/portfolio.component';
import { ContactComponent } from './contact/contact.component';
import { LoginComponent } from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import { CurrencycompComponent } from './login/portfolio/currencycomp/currencycomp.component';

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
    path:'login',
    component:LoginComponent
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
    LoginComponent,
    CurrencycompComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes, {enableTracing:true}),
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
