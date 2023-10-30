import { Component } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import Swal from 'sweetalert2';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css']
})
export class TrainComponent {

  fromStation: string = "";
  toStation: string = "";
  travelDate: string = "";
  noOfPassengers:any='';
  passengerName:string='';
  passengerAge:any='';
  passengersArray:any[]=[];
  trainsForRoutes:any[]= [

  ];
  snack: any;

  constructor(private backservice: BackserviceService) {
}




  onSubmit() {
  //   console.log('From Station:', this.fromStation);
  //  console.log('To Station:', this.toStation);
  //  console.log('Travel Date:', this.travelDate);
  this.backservice.getTrainForRoute(this.fromStation, this.toStation)
  .subscribe(
    (response:any) => {
      // Handle the response from the backend here
      console.log('Response from the backend:', response);
      this.trainsForRoutes=response;
    },
    error => {
      // Handle any errors here
      console.error('Error:', error);
      this.snack.open('Something went wrong!!', '',{
        duration:3000,
      });
    }
  );
 }

 bookNow(train:any[]){
  console.log("bookNow button clicked" +train);
  
  this.backservice.bookTicket(train,this.travelDate)
  .subscribe(
    (response:any) => {
      // Handle the response from the backend here
      console.log('data inserted successfully:', response);
      Swal.fire('Sucess','Ticket Booked  Sucessfully with User_ID '+response.id,'success');
    },
    error => {
      // Handle any errors here
      console.error('Error:', error);
      this.snack.open('Something went wrong!!', '',{
        duration:3000,
      });
    }
  );


 }

}


