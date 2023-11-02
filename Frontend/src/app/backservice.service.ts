import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class BackserviceService {

  constructor(private http: HttpClient, private login:LoginService) {}


//********GEt trains Details for particular routes */
  getTrainForRoute(fromStation:string, toStation:string, travelDate:string):Observable<object>{
    console.log("Get trainForRoutes is called");
    return this.http.get(`http://localhost:8080/test/find/train/${fromStation}/${toStation}/${travelDate}`)
  }

  // *******SIGN UP user********************
  registerUser(user:any):Observable<object>{
    console.log("registerUser is called");
    return this.http.post(`http://localhost:8080/register`,user);
  }


  // *******Book Tickets Here *************************
  bookTicket(details:any, travelDate:string,passengersArray:any,noOfPassengers:any,seatType:any):Observable<object>{
    console.log("bookTicket  is called");
    const token = localStorage.getItem('token');
    console.log("Token is: " + token);
    let email:any;
    if (token) {
      const parts = token.split('.');
      const payload = JSON.parse(atob(parts[1]));
      email = payload.sub;

       console.log("Email from token is: " + email);
    } else {
      console.log("Token not found in localStorage");
    }
    
    console.log(seatType);
    var amount:number=0;
    if(seatType=="seat"){
      amount = (details[6]-details[4])*1.1;
    }
    if(seatType=="sleeper"){
      amount = (details[6]-details[4])*1.25;
    }
    if(seatType=="thirdAC"){
      amount = (details[6]-details[4])*1.5;
    }
    if(seatType=="secondAC"){
      amount = (details[6]-details[4])*1.8;
    }
    if(seatType=="firstAC"){
      amount = (details[6]-details[4])*2.1;
    }
    const seat = details[2]+' '+seatType;
    console.log(amount);
    const jsonTicketDetails = {
      amountPaid : amount*noOfPassengers,
      numberOfTickets : noOfPassengers,
      seatType : seat,
      travelDate: travelDate,
      passengers:passengersArray
    }
    console.log(jsonTicketDetails);
    return this.http.post(`http://localhost:8080/book/${details[7]}/${details[9]}/${details[0]}/${email}`,jsonTicketDetails);
  }


//****GET TRIP DETAILS OF A PARTICULAR USER***** */
  getTripDetailsOfUser(){
    console.log("getTripDetailsOfUser is called from backservice");
    // const token:any = localStorage.getItem('token');
    // console.log("tpken is ***"+token);
    // let payload = token.split('.')[1];
    //   payload = payload.replace(/-/g, '+').replace(/_/g, '/');
    //   payload = decodeURIComponent(window.atob(payload).split('').map(function (c) {
    //     return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    //   }).join(''));
    //   let res = JSON.parse(payload);
    // console.log("email id from token is" , res);
    const userObj:any = localStorage.getItem('user');
    const user = JSON.parse(userObj);
    const id = user.id;
    console.log(id);
    return this.http.get(`http://localhost:8080/find/bookings/${id}`);  

  }

  //****GET TRIP DETAILS OF A PARTICULAR USER***** */
  getUpComingTripDetailsOfUser(){
    console.log("get Upcomming TripDetailsOfUser is called from backservice");
    // const token:any = localStorage.getItem('token');
    // console.log("tpken is ***"+token);
    // let payload = token.split('.')[1];
    //   payload = payload.replace(/-/g, '+').replace(/_/g, '/');
    //   payload = decodeURIComponent(window.atob(payload).split('').map(function (c) {
    //     return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    //   }).join(''));
    //   let res = JSON.parse(payload);
    // console.log("email id from token is" +res);
    const userObj:any = localStorage.getItem('user');
    const user = JSON.parse(userObj);
    const id = user.id;
    console.log(id);
    return this.http.get(`http://localhost:8080/find/bookings/upcomming/${id}`);  
  }

  //***DELETE BOOKING FOR USER ******* */
  deleteBooking(id:any){
    console.log("inside delete from backservice");
    console.log(id);
    return this.http.delete(`http://localhost:8080/delete/booking/${id}`);
    
  }

  //**GEtPASSENGERS LIST FOR SHOW DETAILS**** */
  getPassengersList(id:any){
    console.log("inside getPassengersList from backservice");
    return this.http.get(`http://localhost:8080/find/passengers/${id}`)
  }



}
