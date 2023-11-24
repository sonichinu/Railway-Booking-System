import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { enc, AES } from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  public loginStatusSubject = new Subject<boolean>();

  private secretKey = 'SecretKey';

  //************GEt Current User **********************/
  public getCurrentUSer(){
    return this.http.get(`http://localhost:8080/auth/get-current-user`); 
  }

  //*******************Generate TOken***********************
  public generateToken(email:any,password:any){
    console.log("GenerateToken is called");
    return this.http.post(`http://localhost:8080/auth/login`,{username:email,password:password});
  }

  //***********FOR GOOGLE AUTH****************** */
  public googleAuth(){
    console.log("google auth is called");
    return this.http.get(`http://localhost:8080/login`);
  }

  //*****************Login user set token in local storage***************
  public loginUser(token:any){
    console.log("inside loginuser function login service");
    localStorage.setItem('token',token);
    this.loginStatusSubject.next(true);
    return true;
  }

  //*****************Check For Login*******************************/
  public isLoggedIn(){
    let tokenstr =  localStorage.getItem('token');
    if(tokenstr==undefined || tokenstr==null || tokenstr==''){
      return false;
    }
    else{
      return true;
    }
  }

  //**************Logout: remove token from ls */
  public logout(){
    localStorage.removeItem('token');
    return true;
  }

  //************getToken********** */
  public getToken(){
    return localStorage.getItem('token');
  }

  //*************setUser in ls */
  public setUser(user:any){
    // console.log("this is user befor storing in local storage", JSON.stringify(user));
    const encrypted = AES.encrypt(JSON.stringify(user), this.secretKey);
    localStorage.setItem("user", encrypted.toString());
  }

  //***********get user*************  */
  public getUser(){
    let userStr = localStorage.getItem('user');
    if(userStr!=null){

      const bytes = AES.decrypt(userStr, 'SecretKey');
      const decryptedData = JSON.parse(bytes.toString(enc.Utf8));
      console.log("loginService getuser is called" , decryptedData);
      
      return decryptedData;
    }
    else{
      this.logout();
      return null;
    }
  }









}
