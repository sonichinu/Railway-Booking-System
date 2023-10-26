import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  isUser = false;
  constructor(private router:Router, private login:LoginService){}
  canActivate() {
    if(this.login.isLoggedIn())
    {  
    return true;
    }
    else
    {
      this.router.navigateByUrl('/login');
      return false;
    }
  } 

}