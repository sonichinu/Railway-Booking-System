import { Component, OnInit } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-my-trips',
  templateUrl: './my-trips.component.html',
  styleUrls: ['./my-trips.component.css']
})
export class MyTripsComponent implements OnInit{

  constructor(private backService:BackserviceService, private snack:MatSnackBar){}

  ngOnInit(): void {
    console.log("oninit from my-trips is called");
    
    this.trips();
  }

  allTripsOfUsers:any=[];

  trips():void{
    this.backService
      .getTripDetailsOfUser()
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.allTripsOfUsers = response;
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
    console.log(tripDateTime > currentDateTime);
    
    // If the trip date and time is in the future, it's upcoming
    return tripDateTime > currentDateTime;
  }

  

}
