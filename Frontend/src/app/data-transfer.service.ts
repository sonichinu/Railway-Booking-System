import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataTransferService {

  constructor() { }

  trainDetails:any;
  travelDate:any;


  setTrainDetails(details: any,travelDate:any) {
    this.trainDetails = details;
    this.travelDate= travelDate;

  }

  getTrainDetails() {
    return this.trainDetails;
  }
  getTravelDate(){
    return this.travelDate;
  }

}
