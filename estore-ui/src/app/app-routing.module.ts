import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { InventoryComponent } from './inventory/inventory.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { facilitiesComponent } from './facilities/facilities.component';
import { TeamComponent } from './team/team.component';
import { LeagueComponent } from './league/league.component';
import { StadiumComponent } from './stadium/stadium.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'inventory', component: InventoryComponent},
  {path: 'facilities', component: facilitiesComponent},
  {path: 'team', component: TeamComponent},
  {path: 'league', component: LeagueComponent},
  {path:'stadium', component: StadiumComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
