import { Component } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2'
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(private backservice:BackserviceService, private snack:MatSnackBar, private router: Router){}

  user = {
    name: '',
    email: '',
    password: '',
    phone: '',
    usersname: ''
  };

  formSubmit() {
    // Handle registration logic here
    if(this.user.name==null || this.user.name == ''){
      this.snack.open('name is required!!', '',{
        duration:3000,
      });
      return;
    }
    console.log('Registration data:', this.user);
    this.backservice.registerUser(this.user)
    .subscribe(
      (response:any) => {
        // Handle the response from the backend here
        console.log('data inserted successfully:', response);
        Swal.fire('Sucess','User Registered Sucessfully with userID '+ response.id ,'success');
      },
      error => {
        // Handle any errors here
        console.error('Error:', error);
        this.snack.open('Something went wrong!!', '',{
          duration:3000,
        });
      }
    );
    this.router.navigateByUrl('/login');
   }

   clearForm() {
    // this.user = {
    //   name: '',
    //   email: '',
    //   username: '', 
    //   password:'',
    //   phone:''
    // };
    this.router.navigateByUrl("/signup");
  }
  


  

 

}
