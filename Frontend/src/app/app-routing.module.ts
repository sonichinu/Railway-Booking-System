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
import { MyTripsComponent } from './my-trips/my-trips.component';
import { UpcommingTripsComponent } from './upcomming-trips/upcomming-trips.component';
import { ShowBookingDetailsComponent } from './show-booking-details/show-booking-details.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ListOfUsersComponent } from './list-of-users/list-of-users.component';
import { ListOfTrainsComponent } from './list-of-trains/list-of-trains.component';
import { ListOfStationsComponent } from './list-of-stations/list-of-stations.component';
import { ShowUserTripsComponent } from './show-user-trips/show-user-trips.component';

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
      },
      {
        path:'my-trips',
        component:MyTripsComponent
      },
      {
        path:'upcomming-trips',
        component:UpcommingTripsComponent
      },
      {
        path:'show-booking-details',
        component:ShowBookingDetailsComponent
      }
    ],
    canActivate:[AuthGuard]
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent, 
    canActivate:[AuthGuard],
    children:[
      {
        path:'',
        component:HomeComponent
      },
      {
        path:'list-of-users',
        component:ListOfUsersComponent,
      },
      {
        path:'list-of-trains',
        component:ListOfTrainsComponent
      },
      {
        path:'list-of-stations',
        component:ListOfStationsComponent
      },
      {
        path:'show-user-trips',
        component:ShowUserTripsComponent
      },
      {
        path:'show-booking-details',
        component:ShowBookingDetailsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
