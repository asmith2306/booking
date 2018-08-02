import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Booking} from "../../models/Booking";
import {CustomerBookingsService} from "../../http/rest/customer.bookings.service";
import {RoomsService} from "../../http/rest/rooms.service";
import {Room} from "../../models/Room";
import {Customer} from "../../models/Customer";
import {AppService} from "../../http/rest/app.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-bookings-dashboard',
    templateUrl: './bookings-dashboard.component.html',
    styleUrls: ['./bookings-dashboard.component.css']
})
export class BookingsDashboardComponent implements OnInit {
    bookings: Array<Booking>;
    roomsAvailable: boolean;
    addButtonTooltipText: string

    constructor(private router: Router, private route: ActivatedRoute,
        private bookingsService: CustomerBookingsService, private roomsService: RoomsService,
        private appService: AppService, private snackBar: MatSnackBar) {}

    ngOnInit() {
        this.bookings = this.route.snapshot.data["bookings"];

        this.roomsService.getAllAvailableRooms().subscribe(res => {
            this.roomsAvailable = res.length > 0;
            if (this.roomsAvailable) {
                this.addButtonTooltipText = "Add booking";
            } else {
                this.addButtonTooltipText = "Booked up";
            }
        })
    }

    addBooking() {
        this.roomsService.getAllAvailableRooms().subscribe(res => {
            this.roomsAvailable = res.length > 0;
            if (this.roomsAvailable) {
                this.bookingsService.create(this.appService.activeCustomer.id).subscribe(booking => {
                    this.router.navigate(['main/edit-booking', booking.id], {queryParams: {fromCreate: true}});
                })
            } else {
                this.addButtonTooltipText = "Booked up";
                this.snackBar.open("Sorry, someone just booked the last room! Please try again later", '', {'duration': 5000});
            }
        });
    }

    bookingDeleted(event) {
        this.roomsAvailable = true;
        this.addButtonTooltipText = "Add booking";
    }

}
