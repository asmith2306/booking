import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Booking} from "../../models/Booking";
import {BookingsService} from "../../rest/bookings.service";

@Component({
    selector: 'app-bookings-dashboard',
    templateUrl: './bookings-dashboard.component.html',
    styleUrls: ['./bookings-dashboard.component.css']
})
export class BookingsDashboardComponent implements OnInit {

    bookings: Array<Booking>;

    constructor(private router: Router, private route: ActivatedRoute,
        private bookingsService: BookingsService) {}

    ngOnInit() {
        this.route.data.subscribe((data: {bookings: Array<Booking>}) => {
            this.bookings = data.bookings;
        });
    }

    addBooking() {
        this.bookingsService.create().subscribe(booking => {
            this.router.navigate(['edit-booking', booking.id]);
        })
    }

}
