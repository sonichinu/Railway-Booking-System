import { Component, OnDestroy, OnInit } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import {Subject} from 'rxjs';
import { DataTransferService } from '../data-transfer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-my-trips',
  templateUrl: './my-trips.component.html',
  styleUrls: ['./my-trips.component.css']
})
export class MyTripsComponent implements OnInit, OnDestroy{

  constructor(private backService:BackserviceService, private snack:MatSnackBar, private dataTransfer:DataTransferService){}

  ngOnInit(): void {
    console.log("oninit from my-trips is called");
    
    this.trips();
  }

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();

  allTripsOfUsers:any=[];

  trips():void{
    this.backService
      .getTripDetailsOfUser()
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.allTripsOfUsers = response;
          this.dtTrigger.next

          if (this.allTripsOfUsers === null || this.allTripsOfUsers.length === 0) {
            // Display an alert for no trips yet
            Swal.fire(
              'Error',
              'You Have not Travelled Yet!! ' ,
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
  }

  isUpcomingTrip(travelDate: Date): boolean {
    const currentDateTime = new Date().getTime();
    const tripDateTime = new Date(travelDate).getTime();
    // console.log(tripDateTime > currentDateTime);
    
    // If the trip date and time is in the future, it's upcoming
    return tripDateTime > currentDateTime;
  }


  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  sendTrip(trip:any[]){
    console.log(trip);
    this.dataTransfer.setTrip(trip)
  }

}
