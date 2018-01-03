import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Booking} from "../../models/Booking";

@Component({
    selector: 'app-bookings-dashboard',
    templateUrl: './bookings-dashboard.component.html',
    styleUrls: ['./bookings-dashboard.component.css']
})
export class BookingsDashboardComponent implements OnInit {

    bookings: Array<Booking>;

    constructor(private route: ActivatedRoute) {

    }

    ngOnInit() {
        this.route.data.subscribe((data: {bookings: Array<Booking>}) => {
            this.bookings = data.bookings;
        });
    }

}
