import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ItemDetailComponent } from './item-detail/item-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { HttpClientModule } from '@angular/common/http';
import { ItemSearchComponent } from './item-search/item-search.component';
import { InventoryComponent } from './inventory/inventory.component';
import { TeamComponent } from './team/team.component';
import { LeagueComponent } from './league/league.component';
import { facilitiesComponent } from './facilities/facilities.component';
import { StadiumComponent } from './stadium/stadium.component';
import { InventoryFrontComponent } from './inventory-front/inventory-front.component';
import { PlayerDetailComponent } from './player-detail/player-detail.component';
import { PlayerSearchComponent } from './player-search/player-search.component';
import { TeamDetailComponent } from './team-detail/team-detail.component';
import { FacilityBackComponent } from './facility-back/facility-back.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ItemDetailComponent,
    MessagesComponent,
    ItemSearchComponent,
    InventoryComponent,
    TeamComponent,
    LeagueComponent,
    facilitiesComponent,
    StadiumComponent,
    InventoryFrontComponent,
    PlayerDetailComponent,
    PlayerSearchComponent,
    TeamDetailComponent,
    FacilityBackComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: 'home', component: HomeComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
