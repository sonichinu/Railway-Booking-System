import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule,HttpClient, HTTP_INTERCEPTORS, HttpRequest} from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TrainComponent } from './train/train.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SignupComponent } from './signup/signup.component';
import { MatCardModule ,} from '@angular/material/card'; 
import { MatFormFieldModule } from '@angular/material/form-field'; // Import MatFormFieldModule
import { MatInputModule } from '@angular/material/input'; // Import MatInputModule for input fields
import {MatButtonModule} from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { LoginComponent } from './login/login.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { NavbarComponent } from './navbar/navbar.component';
import { authInterceptorProviders } from './auth.interceptor';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './dashboard/profile/profile.component';
import {MatListModule} from '@angular/material/list';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HomeComponent } from './dashboard/home/home.component';
import { JwtModule } from '@auth0/angular-jwt';
import { BookTicketComponent } from './book-ticket/book-ticket.component';
import { MyTripsComponent } from './my-trips/my-trips.component';
import {DataTablesModule} from 'angular-datatables';
import { UpcommingTripsComponent } from './upcomming-trips/upcomming-trips.component';
import { ShowBookingDetailsComponent } from './show-booking-details/show-booking-details.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminSidebarComponent } from './admin-dashboard/admin-sidebar/admin-sidebar.component';
import { ListOfUsersComponent } from './list-of-users/list-of-users.component';
import { ListOfTrainsComponent } from './list-of-trains/list-of-trains.component';
import { ListOfStationsComponent } from './list-of-stations/list-of-stations.component';
import { ShowUserTripsComponent } from './show-user-trips/show-user-trips.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { AdminprofileComponent } from './admin-dashboard/adminprofile/adminprofile.component';
import { ShowTrainRoutesComponent } from './show-train-routes/show-train-routes.component';




@NgModule({
  declarations: [
    AppComponent,
    TrainComponent,
    SignupComponent,
    LoginComponent,
    NavbarComponent,
    DashboardComponent,
    ProfileComponent,
    SidebarComponent,
    HomeComponent,
    BookTicketComponent,
    MyTripsComponent,
    UpcommingTripsComponent,
    ShowBookingDetailsComponent,
    AdminDashboardComponent,
    AdminSidebarComponent,
    ListOfUsersComponent,
    ListOfTrainsComponent,
    ListOfStationsComponent,
    ShowUserTripsComponent,
    AdminprofileComponent,
    ShowTrainRoutesComponent
  ],
  imports: [
    DataTablesModule,
    MatDatepickerModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MatFormFieldModule,
    MatSnackBarModule,
    MatListModule, 
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
// function tokenGetter(request?: HttpRequest<any> | undefined): string | Promise<string | null> | null {
//   throw new Error('Function not implemented.');
// }

