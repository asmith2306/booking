import {Component, OnInit} from '@angular/core';
import {Booking} from "../../models/Booking";
import {BookingsService} from "../../rest/bookings.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-edit-booking',
    templateUrl: './edit-booking.component.html',
    styleUrls: ['./edit-booking.component.scss']
})
export class EditBookingComponent implements OnInit {

    booking: Booking = new Booking();

    constructor(private route: ActivatedRoute, private router: Router,
        private bookingsService: BookingsService, private snackBar: MatSnackBar) {}

    ngOnInit() {
        this.booking = this.route.snapshot.data["booking"]
    }

    cancelEdit() {
        this.router.navigate(['/']);
    }

    submitDisabled() {
        let disabled = this.booking.checkInDate == null ||
            this.booking.checkOutDate == null ||
            (this.booking.numberOfAdults == null || this.booking.numberOfAdults < 1);

        if (!disabled) {
            disabled = this.booking.checkInDate > this.booking.checkOutDate
        }
        return disabled;
    }

    onSubmit() {
        console.log(this.booking);
        this.bookingsService.update(this.booking).subscribe(booking => {
            // show message and back to dashboard on success
            this.snackBar.open("Booking saved", '', {'duration': 2000});
            this.router.navigate(['/']);
        });
    }

}