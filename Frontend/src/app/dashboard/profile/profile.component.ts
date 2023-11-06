import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BackserviceService } from 'src/app/backservice.service';
import { LoginService } from 'src/app/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user:any=null;

  constructor(private login:LoginService,private backService:BackserviceService, private snack:MatSnackBar){}

  ngOnInit(): void {
    this.user= this.login.getCurrentUSer().subscribe(
      (response:any) => {
        // Handle the response from the backend here
        console.log('User Fetched from backend api successfully:', response);
        this.user= response;
      },
      error => {
        // Handle any errors here
        console.error('Error is:', error);
        this.snack.open('Something went wrong!!', '',{
          duration:3000,
        });
      }
    );
    console.log("user from profile is ", this.user);
    
  }

  editEmail: boolean = false; // Flag to toggle email editing mode
  editPhone: boolean = false;
  editName: boolean = false;
  editUsersname: boolean = false;
  

  onUpdateProfile(): void {
    // Implement this method to handle form submission and update the user's profile
    // You can send the updated data to the server here
    console.log("the profile form is " ,this.user);

    this.backService.updateUser(this.user)
    .subscribe(
      (response:any) => {
        // Handle the response from the backend here
        console.log('data Updated successfully:', response);
        Swal.fire('Sucess','User Updated Sucessfully! ','success');
      },
      error => {
        // Handle any errors here
        console.error('Error is:', error);
        this.snack.open('Something went wrong!!', '',{
          duration:3000,
        });
      }
    );
    
    // After successful update, you can set edit flags to false
    this.editEmail = false;
    this.editName = false;
    this.editPhone = false;
    this.editUsersname = false;
  }

}
