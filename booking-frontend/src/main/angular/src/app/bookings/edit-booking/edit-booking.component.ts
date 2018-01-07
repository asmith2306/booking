import {Component, OnInit} from '@angular/core';
import {Booking} from "../../models/Booking";
import {BookingsService} from "../../rest/bookings.service";
import {ActivatedRoute, Router, Params} from "@angular/router";

@Component({
    selector: 'app-edit-booking',
    templateUrl: './edit-booking.component.html',
    styleUrls: ['./edit-booking.component.scss']
})
export class EditBookingComponent implements OnInit {

    booking: Booking = new Booking();

    constructor(private route: ActivatedRoute, private router: Router,
        private bookingsService: BookingsService) {}

    ngOnInit() {
        this.route.params.forEach((params: Params) => {
            if (params['id'] !== undefined) {
                let id = params['id'];
                this.bookingsService.get(id)
                    .subscribe(booking => {
                        this.booking = booking;
                    });
            }
        });
    }

    cancelEdit() {
        this.router.navigate(['/']);
    }

}