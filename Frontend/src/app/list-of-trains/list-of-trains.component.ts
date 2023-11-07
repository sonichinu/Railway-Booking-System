import { Component, OnInit } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-of-trains',
  templateUrl: './list-of-trains.component.html',
  styleUrls: ['./list-of-trains.component.css']
})
export class ListOfTrainsComponent implements OnInit {

  constructor(private backService:BackserviceService, private snack:MatSnackBar){}

  ngOnInit(): void {
    console.log("oninit from list_of-train is called");
    
    this.getTrains();
  }

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();

  listOfTrains:any = [];

  sendTrain(train:any[]){
    console.log(train);
    // this.dataTransfer.setTrip(trip)
  }

  getTrains():void{
    this.backService
      .getTrainsList()
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.listOfTrains = response;
          this.dtTrigger.next

          if (this.listOfTrains === null || this.listOfTrains.length === 0) {
            // Display an alert for no trips yet
            Swal.fire(
              'Error',
              'No Train Registered Yet ' ,
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
