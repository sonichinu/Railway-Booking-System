import { Component } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import { DataTransferService } from '../data-transfer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-show-user-trips',
  templateUrl: './show-user-trips.component.html',
  styleUrls: ['./show-user-trips.component.css']
})
export class ShowUserTripsComponent {

  constructor(private backService:BackserviceService, private snack:MatSnackBar, private dataTransfer:DataTransferService){}

  ngOnInit(): void {
    console.log("oninit from show-user-trips is called");
    
    this.trips();
  }

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();

  allTripsOfParticularUser:any=[];

  trips():void{
    this.backService
      .getTripDetails(this.user[0])
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.allTripsOfParticularUser = response;
          this.dtTrigger.next

          if (this.allTripsOfParticularUser === null || this.allTripsOfParticularUser.length === 0) {
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

  user= this.dataTransfer.getUser();

  

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  sendTrip(trip:any[]){
    console.log(trip);
    this.dataTransfer.setTrip(trip)
  }

}
