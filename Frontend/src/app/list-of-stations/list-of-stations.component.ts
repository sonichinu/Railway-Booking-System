import { Component } from '@angular/core';
import { Subject } from 'rxjs';
import Swal from 'sweetalert2';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-list-of-stations',
  templateUrl: './list-of-stations.component.html',
  styleUrls: ['./list-of-stations.component.css']
})
export class ListOfStationsComponent {

  constructor(private backService:BackserviceService, private snack:MatSnackBar){}

  ngOnInit(): void {
    console.log("oninit from list_of-stations is called");
    
    this.getStations();
  }

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();

  listOfStations:any = [];

  sendStation(station:any[]){
    console.log(station);
    // this.dataTransfer.setTrip(trip)
  }

  getStations():void{
    this.backService
      .getStationsList()
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.listOfStations = response;
          this.dtTrigger.next

          if (this.listOfStations === null || this.listOfStations.length === 0) {
            // Display an alert for no trips yet
            Swal.fire(
              'Error',
              'No Station Registered Yet ' ,
              'error'
            );
          }

        },
        (error: any) => {
          // Handle any errors here
          console.error('Error:', error);
          this.snack.open('Something went wrong!!', '', {
            duration: 3000,
          });
        }
      );
  }

}
