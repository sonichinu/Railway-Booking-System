import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(public login:LoginService){}

  isLoggedIn= false;
  user:any = null;
  

  ngOnInit():void{
    console.log("onInit is called from navbar ts");
    this.login.loginStatusSubject.asObservable().subscribe(date=>{
      this.isLoggedIn= this.login.isLoggedIn();
      this.user= this.login.getUser();
    });
    this.isLoggedIn= this.login.isLoggedIn();
    this.user= this.login.getUser();
  }

  logout(){
    console.log("logout button clicked");
    this.isLoggedIn=false;
    this.login.logout();
    // window.localStorage.clear();
    this.user=null;
    window.location.reload();
    console.log("logout button clicked");
  }

}
