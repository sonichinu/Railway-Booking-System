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
    BookTicketComponent
  ],
  imports: [
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        // Other configuration options
      },
    }),
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
    BrowserAnimationsModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
function tokenGetter(request?: HttpRequest<any> | undefined): string | Promise<string | null> | null {
  throw new Error('Function not implemented.');
}

