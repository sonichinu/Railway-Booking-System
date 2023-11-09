import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { DataTransferService } from '../data-transfer.service';

@Component({
  selector: 'app-list-of-users',
  templateUrl: './list-of-users.component.html',
  styleUrls: ['./list-of-users.component.css']
})
export class ListOfUsersComponent implements OnInit, OnDestroy  {


  constructor(private backService:BackserviceService, private snack:MatSnackBar, private router:Router, private dataTransfer: DataTransferService){}

  ngOnInit(): void {
    console.log("oninit from list_of-users is called");
    
    this.getUsers();
  }

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();

  listOfUsers:any = [];

  sendUser(user:any[]){
    console.log(user);
    this.dataTransfer.setParticularUser(user)
    this.router.navigateByUrl('/admin-dashboard/show-user-trips');
    
  }

  getUsers():void{
    this.backService
      .getUserList()
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.listOfUsers = response;
          this.dtTrigger.next('');

          if (this.listOfUsers === null || this.listOfUsers.length === 0) {
            // Display an alert for no trips yet
            Swal.fire(
              'Error',
              'No User Registered Yet ' ,
              'error'
            );
          }

        },
        (error) => {
          // Handle any errors here
          console.error('Error:', error);
          this.snack.open('Something went wrong!!', '', {
            duration: 3000,
          });
        }
      );
  }


  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

}
