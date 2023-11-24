import { Component, OnInit } from '@angular/core';
import { DataTransferService } from '../data-transfer.service';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ADTSettings } from 'angular-datatables/src/models/settings';

@Component({
  selector: 'app-show-train-routes',
  templateUrl: './show-train-routes.component.html',
  styleUrls: ['./show-train-routes.component.css']
})
export class ShowTrainRoutesComponent implements OnInit{


  constructor(private dataTransfer:DataTransferService, private backservice:BackserviceService, private snack:MatSnackBar){
    this.train= dataTransfer.getTrain();
    console.log("train from show train details comp is " + this.train);
    
  }


  ngOnInit(): void {
    console.log("oninit from show-train-routes is called");
    console.log(this.train[0]);
    this.getTrainRoutes(this.train[0]);
  }

  train:any = [];
  trainRoutes:any=[];

  getTrainRoutes(trainId:any):void{
    this.backservice
      .getTrainRoutes(trainId)
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.trainRoutes = response;
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
