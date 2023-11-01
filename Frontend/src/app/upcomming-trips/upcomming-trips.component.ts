import { Component } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-upcomming-trips',
  templateUrl: './upcomming-trips.component.html',
  styleUrls: ['./upcomming-trips.component.css']
})
export class UpcommingTripsComponent {

  constructor(private backService:BackserviceService, private snack:MatSnackBar){}

  ngOnInit(): void {
    console.log("oninit from UpComing-trips is called");
    
    this.trips();
  }

  allUpComingTripsOfUsers:any=[];

  trips():void{
    this.backService
      .getUpComingTripDetailsOfUser()
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.allUpComingTripsOfUsers = response;
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
