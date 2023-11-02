import { Component, OnInit } from '@angular/core';
import { DataTransferService } from '../data-transfer.service';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-show-booking-details',
  templateUrl: './show-booking-details.component.html',
  styleUrls: ['./show-booking-details.component.css']
})
export class ShowBookingDetailsComponent implements OnInit {


  constructor(private dataTransfer:DataTransferService, private backservice:BackserviceService, private snack:MatSnackBar){
    this.trip =this.dataTransfer.getTrip();
    console.log("from constructor" +this.trip);
    
  }
  ngOnInit(): void {
    console.log("oninit from show-booking-details is called");
    console.log(this.trip[9]);
    
    this.getDetails(this.trip[9]);
  }

  trip:any=[];

  passengers: any[] = [

  ];

  getDetails(bookingId:any):void{
    this.backservice
      .getPassengersList(bookingId)
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.passengers = response;
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



}
