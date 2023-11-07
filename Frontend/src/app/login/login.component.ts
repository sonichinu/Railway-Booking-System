import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router, private snack:MatSnackBar, private login:LoginService){}

  loginData = {
    username:'',
    password:'' 
  }

  formSubmit(){
    console.log("login button clicked");
    if(this.loginData.username.trim() == '' || this.loginData.username == null){
      this.snack.open('username is required!!', '',{
        duration:3000,
      });
      return;
    }
    if(this.loginData.password.trim() == '' || this.loginData.password == null){
      this.snack.open('password is required!!', '',{
        duration:3000,
      });
      return;
    }

    this.login.generateToken(this.loginData.username, this.loginData.password).subscribe(
      (response:any) => {
        // Handle the response from the backend here
        console.log('Sucessfully Logged In', response); 
        this.login.loginUser(response.accessToken);
        this.login.getCurrentUSer().subscribe(
          (user:any)=>{
            this.login.setUser(user);
            console.log(user);
            if(user!= null && user.role == null){
              this.router.navigateByUrl('/dashboard');
              // this.login.loginStatusSubject.next(true);
            }
            else if(user!=null && user.role == 'Admin'){
              this.router.navigateByUrl('/admin-dashboard')
            }
            else{
              this.login.logout();
            }
          }
        )
      },
      error => {
        // Handle any errors here
        console.error('Error:', error);
        this.snack.open('Invalid Crenditals!!', '',{
          duration:3000,
        });
      }
    );
  }













  

}


