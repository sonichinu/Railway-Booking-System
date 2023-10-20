import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackserviceService {

  constructor(private http: HttpClient) {}

  getTrainForRoute(fromStation:string, toStation:string):Observable<object>{
    console.log("Get trainForRoutes is called");
    return this.http.get(`http://localhost:8080/find/train/${fromStation}/${toStation}`)
  }

  registerUser(user:any){
    console.log("registerUser is called");
    return this.http.post(`http://localhost:8080/register`,user);
  }

}
