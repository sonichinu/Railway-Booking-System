import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import * as jwt_decode from 'jwt-decode';
import { AES, enc } from 'crypto-js';

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
  bookTicket(details:any, travelDate:string,passengersArray:any,noOfPassengers:any,seatType:any,amount:any):Observable<object>{
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
    
    return this.http.post(`http://localhost:8080/book/${details[7]}/${details[9]}/${details[0]}/${email}/${details[3]}/${details[5]}  `,jsonTicketDetails);
  }


//****GET TRIP DETAILS OF A PARTICULAR USER***** */
  getTripDetailsOfUser(){
    console.log("getTripDetailsOfUser is called from backservice");
    const userObj:any = localStorage.getItem('user');

    const bytes = AES.decrypt(userObj, 'SecretKey');
    const decryptedData = JSON.parse(bytes.toString(enc.Utf8));
    const id = decryptedData.id;
    console.log(id);
    return this.http.get(`http://localhost:8080/find/bookings/${id}`);  

  }

  getTripDetails(id:any){
    console.log("getTripDetailsOfUser is called from backservice");
    return this.http.get(`http://localhost:8080/find/bookings/${id}`); 

  }

  //****GET TRIP DETAILS OF A PARTICULAR USER***** */
  getUpComingTripDetailsOfUser(){
    console.log("get Upcomming TripDetailsOfUser is called from backservice");
    const userObj:any = localStorage.getItem('user');

    const bytes = AES.decrypt(userObj, 'SecretKey');
    const decryptedData = JSON.parse(bytes.toString(enc.Utf8));

    // const user = JSON.parse(userObj);
    const id = decryptedData.id;
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

  // *******Update USerApi ********************
  updateUser(user:any):Observable<object>{
    console.log("updateUser is called",user);
    const userJson ={
      id: user.id,
      name:user.name,
      phone:user.phone,
      usersname:user.usersname,
      password:user.password,
      email:user.email
    }
    console.log("userjosn is ",userJson);
    
    return this.http.put(`http://localhost:8080/update`,userJson);
  }

   //**GEt USERS  FOR ADMIN PAnnel**** */
   getUserList(){
    console.log("inside getUserList from backservice");
    return this.http.get(`http://localhost:8080/find/users/find-only-users`);
  }


  //**GEt Trains  FOR ADMIN PAnnel**** */
  getTrainsList(){
    console.log("inside getTrainsList from backservice");
    return this.http.get(`http://localhost:8080/test/find/trains/find-only-trains`);
  }

  //**GEt Stations  FOR ADMIN PAnnel**** */
  getStationsList(){
    console.log("inside getStationsList from backservice");
    return this.http.get(`http://localhost:8080/find/stations/find-only-stations`);
  }

   //**GEt TRAIN ROUTES   FOR ADMIN PAnnel**** */
   getTrainRoutes(id:any){
    console.log("inside getTrainROutes from backservice");
    return this.http.get(`http://localhost:8080/test/find/train-routes/${id}`);
  }

  // *******INITIALIZE PAYMENT********************
  initializePayment(amount:number):Observable<object>{
    console.log("initialize payment is called");
    const intAmount = Math.round(amount); // Convert to integer representation of amount
    return this.http.get(`http://localhost:8080/initializePayment/${intAmount}`);
    // return this.http.get(`http://localhost:8080/initializePayment/${amount}`);
  }
}
