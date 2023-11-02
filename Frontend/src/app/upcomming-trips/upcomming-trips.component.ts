import { Component } from '@angular/core';
import { BackserviceService } from '../backservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-upcomming-trips',
  templateUrl: './upcomming-trips.component.html',
  styleUrls: ['./upcomming-trips.component.css']
})
export class UpcommingTripsComponent {

  constructor(private backService:BackserviceService, private snack:MatSnackBar){}

  ngOnInit(): void {
    console.log("oninit from UpComing-trips is called");
    
    this.trips();
  }

  allUpComingTripsOfUsers:any=[];

  trips():void{
    this.backService
      .getUpComingTripDetailsOfUser()
      .subscribe(
        (response: any) => {
          // Handle the response from the backend here
          console.log('Response from the backend:', response);
          this.allUpComingTripsOfUsers = response;
          if (this.allUpComingTripsOfUsers === null || this.allUpComingTripsOfUsers.length === 0) {
          Swal.fire(
            'Error',
            'No Upcoming Trips!! ' ,
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

  cancelBooking(bookingId:number, bookingIndex: number) {
    Swal.fire({
        title: 'Are you sure?',
        text: 'You are about to cancel this booking!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, cancel it!',
    }).then((result) => {
        if (result.isConfirmed) {
            // First, remove the booking from the local array to update the UI immediately.
            this.allUpComingTripsOfUsers.splice(bookingIndex, 1);

            // Then, call the API to delete the booking. Ensure you handle API errors appropriately.
            this.backService.deleteBooking(bookingId)
                .subscribe(
                    () => {
                        // API call was successful. You can show a success message.
                        Swal.fire('Canceled!', 'Your booking has been canceled.', 'success');
                    },
                    (error) => {
                        // Handle API call errors here. You may want to add the booking back to the array if deletion failed.
                        console.error('Booking cancellation failed:', error);
                        Swal.fire('Error', 'Booking cancellation failed.', 'error');
                    }
                );
        }
    });
}

}
