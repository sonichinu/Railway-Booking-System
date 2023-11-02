import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user:any=null;

  constructor(private login:LoginService){}

  ngOnInit(): void {
    this.user= this.login.getUser();
    console.log("user from profile is ", this.user);
    
  }

  editEmail: boolean = false; // Flag to toggle email editing mode
  editPhone: boolean = false;
  editName: boolean = false;
  editUsername: boolean = false;
  updatedUser:any = {}

  onUpdateProfile(): void {
    // Implement this method to handle form submission and update the user's profile
    // You can send the updated data to the server here
    console.log("the profile form is " ,this.updatedUser);
    
    // After successful update, you can set edit flags to false
    this.editEmail = false;
    this.editName = false;
    this.editPhone = false;
    this.editUsername = false;
  }

}
