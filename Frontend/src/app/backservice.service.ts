import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class BackserviceService {

  constructor(private http: HttpClient, private login:LoginService, private jwtHelper: JwtHelperService) {}


//********GEt trains Details for particular routes */
  getTrainForRoute(fromStation:string, toStation:string):Observable<object>{
    console.log("Get trainForRoutes is called");
    return this.http.get(`http://localhost:8080/test/find/train/${fromStation}/${toStation}`)
  }

  // *******SIGN UP user********************
  registerUser(user:any):Observable<object>{
    console.log("registerUser is called");
    return this.http.post(`http://localhost:8080/register`,user);
  }


  // *******Book Tickets Here *************************
  bookTicket(details:any, travelDate:string,passengersArray:any,noOfPassengers:any,seatType:any):Observable<object>{
    console.log("bookTicket  is called");
    // const token:any = localStorage.getItem('token');
    // console.log(token);
    // const decodedToken:any = this.jwtHelper.decodeToken(token);
    // const email = decodedToken.id
    // console.log("email id from token is" +email)

    const seat = details[2]+' '+seatType;
    
    const jsonTicketDetails = {
      amountPaid : (details[6]-details[4])*1.25,
      numberOfTickets : noOfPassengers,
      seat : seat,
      travelDate: travelDate,
      passengers:passengersArray
    }
    console.log(jsonTicketDetails);
    

    return this.http.post(`http://localhost:8080/book/${details[7]}/${details[9]}/${details[0]}/a@gmail.com`,jsonTicketDetails);
  }
}
