import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { InventoryComponent } from './inventory/inventory.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { facilitiesComponent } from './facilities/facilities.component';
import { TeamComponent } from './team/team.component';
import { LeagueComponent } from './league/league.component';
import { PlayerDetailComponent } from './player-detail/player-detail.component';
import { TeamDetailComponent } from './team-detail/team-detail.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'inventory', component: InventoryComponent},
  {path: 'facilities', component: facilitiesComponent},
  {path: 'team', component: TeamComponent},
  {path: 'league', component: LeagueComponent},
  {path: 'detail/:name', component: PlayerDetailComponent},
  {path: 'detail/:id', component: TeamDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
