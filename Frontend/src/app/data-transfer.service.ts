import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataTransferService {

  constructor() { }

  trainDetails:any;
  travelDate:any;
  tripOfUser:any=[];
  particularUser:any;
  train:any;


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

  setTrip(tripDetails:any[]){
    this.tripOfUser=tripDetails;
  }

  getTrip(){
    return this.tripOfUser;
  }

  setParticularUser(user:any[]){
    this.particularUser=user;
  }
  
  getUser(){
    return this.particularUser;
  }

  getTrain(){
    return this.train;
  }

  setTrain(train:any[]){
    this.train=train;
  }

}
