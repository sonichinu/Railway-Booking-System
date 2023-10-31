import { Component } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import Swal from 'sweetalert2';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { DataTransferService } from '../data-transfer.service';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css'],
})
export class TrainComponent {
  fromStation: string = '';
  toStation: string = '';
  travelDate: string = '';
  noOfPassengers: any='';
  passengerName: string = '';
  passengerAge: any = '';
  passengersArray: any[] = [];
  trainsForRoutes: any[] = [];
  snack: any;


  constructor(private backservice: BackserviceService, private router:Router, private dataTransfer:DataTransferService) {
  }
  
  

  // Function to initialize passengersArray
  initializePassengersArray() {
    this.passengersArray = Array(this.noOfPassengers);
  }

  onSubmit() {
    this.backservice
      .getTrainForRoute(this.fromStation, this.toStation)
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.trainsForRoutes = response;
        },
        (error) => {
          // Handle any errors here
          console.error('Error:', error);
          this.snack.open('Something went wrong!!', '', {
            duration: 3000,
          });
        }
      );
  }

  bookNow(train: any[]) {
    console.log('bookNow button clicked' + train);
    this.dataTransfer.setTrainDetails(train,this.travelDate);
    this.router.navigateByUrl('/dashboard/book-ticket');

  }
}