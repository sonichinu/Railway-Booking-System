import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { TrainComponent } from './train/train.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthGuard } from './auth.guard';
import { ProfileComponent } from './dashboard/profile/profile.component';
import { HomeComponent } from './dashboard/home/home.component';
import { BookTicketComponent } from './book-ticket/book-ticket.component';

const routes: Routes = [
  {
    path: 'signup',
    component: SignupComponent,
    pathMatch: 'full'
  },
  {
    path: 'trainforroutes',
    component: TrainComponent,
    pathMatch: 'full',
    canActivate:[AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    // pathMatch: 'full',
    children:[
      {
        path:'',
        component:HomeComponent
      },
      {
        path:'profile',
        component:ProfileComponent
      },
      {
        path:'trainforroutes',
        component:TrainComponent,
      },
      {
        path:'book-ticket',
        component:BookTicketComponent
      }
    ],
    canActivate:[AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
