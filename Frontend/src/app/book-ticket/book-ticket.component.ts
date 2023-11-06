import { Component } from '@angular/core';
import { DataTransferService } from '../data-transfer.service';
import { BackserviceService } from '../backservice.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-ticket',
  templateUrl: './book-ticket.component.html',
  styleUrls: ['./book-ticket.component.css']
})
export class BookTicketComponent {
  snack: any;

  constructor(private dataTransfer: DataTransferService,private bacservice: BackserviceService, private router:Router){
    this.trainDetails = this.dataTransfer.getTrainDetails();
    console.log(this.trainDetails);
    
  }

  selectedSeatType: string = '';
  trainDetails: any;
  noOfPassengers: any='';
  passengersArray: { name: string, age: number , gender: string}[] = [];
  onUse() {
    console.log(this.noOfPassengers);
    if(this.noOfPassengers>6){
      Swal.fire(
        'Error',
        'Only Six Bookings Allowed At a Time ' ,
        'error'
      );
      this.noOfPassengers=6;
    }
    // Initialize passengersArray with the specified number of passengers
    this.initializePassengersArray();
  }

  initializePassengersArray() {
    this.passengersArray = new Array(this.noOfPassengers).fill(null).map(() => ({ name: '', age: 0, gender: '' }));
  }
  

  sendDetails() {
    console.log('Passenger details:', this.passengersArray);
    this.bacservice.bookTicket(this.trainDetails, this.dataTransfer.getTravelDate(),this.passengersArray,this.noOfPassengers,this.selectedSeatType).subscribe(
      (response: any) => {
        // Handle the response from the backend here
        console.log('data inserted successfully:', response);
        Swal.fire(
          'Sucess',
          'Ticket Booked  Sucessfully with Booking_ID ' + response.id,
          'success'
        );
      },
      (error) => {
        // Handle any errors here
        console.error('Error:', error);
        this.snack.open('Something went wrong!!', '', {
          duration: 3000,
        });
      }
    );
    this.router.navigateByUrl("/dashboard");
  }

  onSeatTypeChange(seatType: string) {
    this.selectedSeatType = seatType;
    // You can add any logic here to handle the selected seat type
    console.log('Selected Seat Type:', this.selectedSeatType);
  } 

}
