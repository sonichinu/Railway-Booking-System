import { Component } from '@angular/core';
import { BackserviceService } from '../backservice.service';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css']
})
export class TrainComponent {

  fromStation: string = "";
  toStation: string = "";
  travelDate: string = "";
  trainsForRoutes:any[]= [

  ];

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
    }
  );
 }

 bookNow(train:any[]){
  console.log("bookNow button clicked" +train);
 }

}


