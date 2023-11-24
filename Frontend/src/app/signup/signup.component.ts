import { Component, OnInit } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2'
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{

  userForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,private backservice:BackserviceService, private snack:MatSnackBar, private router: Router, private loginService: LoginService) {}


  ngOnInit(): void {
    this.userForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      username: new FormControl('', [Validators.required, Validators.minLength(5)]),
      phone: new FormControl('', [Validators.required, Validators.minLength(10)]),
       email: new FormControl('', [Validators.required, Validators.email]),
       password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$')]),
      
    })
  }


  formSubmit(form:FormGroup) {
      if (this.userForm.valid) {
          // If the form is valid, proceed with form submission or further actions
          console.log('Form submitted:', this.userForm.value);
          // Call your API, perform actions, etc.
          this.backservice.registerUser(this.userForm.value)
    .subscribe(
      (response:any) => {
        // Handle the response from the backend here
        console.log('data inserted successfully:', response);
        Swal.fire('Sucess','User Registered Sucessfully with userID '+ response.id ,'success');
        this.router.navigateByUrl('/login');
      },
      error => {
        // Handle any errors here
        console.error('Error:', error);
        console.log(error);
        
        if(error.error.message == "User Already Exists with this email"){
          this.snack.open('Email Already Exists!!', '',{
            duration:3000,
          });
        }else{
          this.snack.open('Something went wrong!!', '',{
            duration:3000,
          });
        }
        
      }
    );
   

      } else {
          // If the form is invalid, show error messages or handle accordingly
          console.log('Form is invalid. Please check the fields.');
          // You can also highlight the invalid fields or display specific error messages
          // For instance:
          this.userForm.markAllAsTouched(); // Marks all fields as touched to display error messages
          this.snack.open('Something went wrong!!', '',{
            duration:3000,
          });
      }
  }

  clearForm() {
      this.userForm.reset();
  }

 
}
