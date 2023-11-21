import { Component, OnInit } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import Swal from 'sweetalert2';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { DataTransferService } from '../data-transfer.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css'],
})
export class TrainComponent implements OnInit{

  trainForm!: FormGroup;
  // fromStation: string = '';
  // toStation: string = '';
  // travelDate: string = '';
  noOfPassengers: any='';
  passengerName: string = '';
  passengerAge: any = '';
  passengersArray: any[] = [];
  trainsForRoutes: any[] = [];
  snack: any;


  constructor(private backservice: BackserviceService, private router:Router, private dataTransfer:DataTransferService) {}
  
  
  ngOnInit(): void {
    this.trainForm = new FormGroup({
      fromStation: new FormControl('', [Validators.required, Validators.minLength(3), Validators.pattern("^[a-zA-Z ]*$")]),
      toStation: new FormControl('', [Validators.required, Validators.minLength(3)]),
      travelDate: new FormControl('', [Validators.required, Validators.minLength(1)])
    })
   
  }
  
  

  // Function to initialize passengersArray
  initializePassengersArray() {
    this.passengersArray = Array(this.noOfPassengers);
  }

  onSubmit(form:FormGroup) {
    if (this.trainForm.valid) {
      this.backservice
        .getTrainForRoute(form.value.fromStation, form.value.toStation,form.value.travelDate)
        .subscribe(
          (response: any) => {
            // Handle the response from the backend here
            console.log('Response from the backend:', response);
            this.trainsForRoutes = response;
            if (this.trainsForRoutes === null || this.trainsForRoutes.length === 0) {
              // Display an alert for no trips yet
              Swal.fire(
                'Error',
                'No Train found for this route ' ,
                'error'
              );
            }
          },
          (error) => {
            // Handle any errors here
            console.error('Error:', error);
            this.snack.open('Something went wrong!!', '', {
              duration: 3000,
            });
          }
        );
    }else {
      // If the form is invalid, show error messages or handle accordingly
      console.log('Form is invalid. Please check the fields.');
      // You can also highlight the invalid fields or display specific error messages
      // For instance:
      this.trainForm.markAllAsTouched(); // Marks all fields as touched to display error messages
      this.snack.open('Something went wrong!!', '',{
        duration:3000,
      });
  }
  }

  bookNow(train: any[]) {
    console.log('bookNow button clicked' + train);
    this.dataTransfer.setTrainDetails(train,this.trainForm.value.travelDate);
    this.router.navigateByUrl('/dashboard/book-ticket');

  }


}