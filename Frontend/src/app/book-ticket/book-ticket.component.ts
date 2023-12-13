import { Component, Injectable, OnInit } from '@angular/core';
import { DataTransferService } from '../data-transfer.service';
import { BackserviceService } from '../backservice.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { post } from 'jquery';
import { LoginService } from '../login.service';

declare var Razorpay: any;

@Component({
  selector: 'app-book-ticket',
  templateUrl: './book-ticket.component.html',
  styleUrls: ['./book-ticket.component.css']
})
export class BookTicketComponent {
  snack: any;
  rzp: any;
  user: any;
  loading = false;


  constructor(private dataTransfer: DataTransferService, private bacservice: BackserviceService, private router: Router, private login: LoginService) {
    this.trainDetails = this.dataTransfer.getTrainDetails();
    console.log(this.trainDetails);

    this.user = this.login.getCurrentUSer().subscribe(
      (response: any) => {
        // Handle the response from the backend here
        console.log('User Fetched from backend api successfully:', response);
        this.user = response;
      },
      error => {
        // Handle any errors here
        console.error('Error is:', error);
        this.snack.open('Something went wrong!!', '', {
          duration: 3000,
        });
      }
    );
    console.log("user from book ticket CONSTRUCTOR is ", this.user);
  }


  selectedSeatType: string = '';
  trainDetails: any;
  noOfPassengers: any = '';
  passengersArray: { name: string, age: any, gender: string }[] = [];
  paymentId: any = '';
  totalAmount: any;
  onUse() {
    console.log(this.noOfPassengers);
    if (this.noOfPassengers > 6) {
      Swal.fire(
        'Error',
        'Only Six Bookings Allowed At a Time ',
        'error'
      );
      this.noOfPassengers = 6;
    }
    // Initialize passengersArray with the specified number of passengers
    this.initializePassengersArray();
  }

  initializePassengersArray() {
    this.passengersArray = new Array(this.noOfPassengers).fill(null).map(() => ({ name: '', age: '', gender: '' }));
  }


  // sendDetails() {
  //   this.loading = true;
  //   console.log('Passenger details:', this.passengersArray);
  //   if (this.isPassengerDataValid()) {
  //     this.bacservice.initializePayment(this.totalAmount).subscribe(
  //       (response: any) => {
  //         // Handle the response from the backend here
  //         console.log('payment response from backend', response);
  //         if (response.status == "created") {
  //           this.rzp = new Razorpay({
  //             key: 'rzp_test_Lt6Kiszh9cXocL', // Replace with your Razorpay key
  //             name: "Rohit's IRCTC Booking System",
  //             description: "This is a Dummy IRCTC APP developed By Rohit Soni...",
  //             currency: 'INR',
  //             amount: response.amount,
  //             order_id: response.id,
  //             handler: (responseFront: any) => {
  //               console.log("frontResponse is", responseFront);
  //               // Handle payment success
  //               console.log('Payment Id:', responseFront.razorpay_payment_id);
  //               console.log('Order Id:', response.razorpay_order_id);
  //               console.log('Signature:', response.razorpay_signature);
  //               this.paymentId = responseFront.razorpay_payment_id;
  //               if (responseFront.razorpay_payment_id != null && responseFront.razorpay_payment_id != '') {
  //                 console.log("inside if");
  //                 this.loading = true;
  //                 console.log(this.loading, "value is ..........");

  //                 this.bacservice.bookTicket(this.trainDetails, this.dataTransfer.getTravelDate(), this.passengersArray, this.noOfPassengers, this.selectedSeatType, this.totalAmount).subscribe(
  //                   (response: any) => {
  //                     // Handle the response from the backend here
  //                     console.log('data inserted successfully:', response);
  //                     this.loading = false;
  //                     Swal.fire(
  //                       'Sucess',
  //                       'Ticket Booked  Sucessfully with Booking_ID ' + response.id,
  //                       'success'
  //                     );
  //                     this.router.navigateByUrl("/dashboard");
  //                   },
  //                   (error: any) => {
  //                     // Handle any errors here
  //                     console.error('Error:', error);
  //                     this.loading = false;
  //                     this.snack.open('Something went wrong!!', '', {
  //                       duration: 3000,
  //                     });
  //                   }
  //                 );

  //               }
  //             },
  //             prefill: {
  //               name: this.user.name,
  //               email: this.user.email,
  //               contact: this.user.phone
  //             },
  //             theme: {
  //               color: '#F37254'
  //             }
  //           });
  //         }
  //         this.rzp.open();
  //       },
  //       (error: any) => {
  //         // Handle any errors here
  //         console.error('Error:', error);
  //         this.snack.open('Something went wrong!!', '', {
  //           duration: 3000,
  //         });
  //       }
  //     );
  //   }
  // }

  sendDetails() {
    console.log('Passenger details:', this.passengersArray);
    if(this.isPassengerDataValid()){
      this.bacservice.bookTicket(this.trainDetails, this.dataTransfer.getTravelDate(),this.passengersArray,this.noOfPassengers,this.selectedSeatType, this.totalAmount).subscribe(
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
  }

  onSeatTypeChange(seatType: string) {
    this.selectedSeatType = seatType;
    // You can add any logic here to handle the selected seat type
    console.log('Selected Seat Type:', this.selectedSeatType);
  }

  isPassengerDataValid(): boolean {
    // Check if any passenger field is empty or if age is less than or equal to 1
    return this.passengersArray.every(
      passenger =>
        passenger.name.trim().length > 0 && // Check if name is not blank
        passenger.age > 1 && // Check if age is greater than 1
        passenger.gender.trim().length > 0
      /* Add more conditions if needed */
    ) && this.selectedSeatType.trim().length > 0;
  }


  passengerNameTouched: boolean[] = [];
  passengerAgeTouched: boolean[] = [];

  markNameAsTouched(index: number): void {
    this.passengerNameTouched[index] = true;
  }

  markAgeAsTouched(index: number): void {
    this.passengerAgeTouched[index] = true;
  }

  // Function to track field touch
  onPassengerNameTouched(index: number): void {
    this.passengerNameTouched[index] = true;
  }

  isPassengerNameInvalid(index: number): boolean {
    const nameControl = this.passengersArray[index].name;
    if (this.passengerNameTouched[index]) {
      return !nameControl || nameControl.trim().length === 0;
    }
    return false; // Field not yet touched, so not invalid
  }

  onPassengerAgeTouched(index: number): void {
    this.passengerAgeTouched[index] = true;
  }

  isPassengerAgeInvalid(index: number): boolean {
    const ageControl = this.passengersArray[index].age;
    if (this.passengerAgeTouched[index]) {
      return !ageControl || isNaN(ageControl) || ageControl <= 0;
    }
    return false; // Field not yet touched, so not invalid
  }

  ifSeatsAvailable() {
    if (this.selectedSeatType === "seat" && this.trainDetails[11] - this.noOfPassengers >= 0) {
      this.totalAmount = (this.trainDetails[6] - this.trainDetails[4]) * 1.1 * this.noOfPassengers;
      console.log("total amount for seat", this.totalAmount);
      return true;
    } else if (this.selectedSeatType === "sleeper" && this.trainDetails[12] - this.noOfPassengers >= 0) {
      this.totalAmount = (this.trainDetails[6] - this.trainDetails[4]) * 1.25 * this.noOfPassengers;
      console.log("total amount for sleeper", this.totalAmount);
      return true;
    } else if (this.selectedSeatType === "thirdAC" && this.trainDetails[13] - this.noOfPassengers >= 0) {
      this.totalAmount = (this.trainDetails[6] - this.trainDetails[4]) * 1.5 * this.noOfPassengers;
      console.log("total amount for third ac", this.totalAmount);
      return true;
    } else if (this.selectedSeatType === "secondAC" && this.trainDetails[14] - this.noOfPassengers >= 0) {
      this.totalAmount = (this.trainDetails[6] - this.trainDetails[4]) * 1.8 * this.noOfPassengers;
      console.log("total amount for second ac", this.totalAmount);
      return true;
    } else if (this.selectedSeatType === "firstAC" && this.trainDetails[15] - this.noOfPassengers >= 0) {
      console.log(this.trainDetails[15] - this.noOfPassengers >= 0, "answer for if");

      this.totalAmount = (this.trainDetails[6] - this.trainDetails[4]) * 2.1 * this.noOfPassengers;
      console.log("total amount for first ac", this.totalAmount);
      return true;
    }
    return false;
  }

  isNoOfPassengersTouched: boolean = false;

  markNoOfPassengersAsTouched() {
    this.isNoOfPassengersTouched = true;
  }

  isNoOfPassengersInvalid(): boolean {
    return this.isNoOfPassengersTouched && (this.noOfPassengers === undefined || this.noOfPassengers <= 0);
  }


}
