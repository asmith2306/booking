import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Booking} from "../../models/Booking";
import {BookingsService} from "../../rest/bookings.service";
import {RoomsService} from "../../rest/rooms.service";
import {Room} from "../../models/Room";

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
        private bookingsService: BookingsService, private roomsService: RoomsService) {}

    ngOnInit() {
        this.bookings = this.route.snapshot.data["bookings"];
        let availableRooms: Array<Room> = this.route.snapshot.data["availableRooms"];
        this.roomsAvailable = availableRooms.length > 0;
        if (this.roomsAvailable) {
            this.addButtonTooltipText = "Add booking";
        } else {
            this.addButtonTooltipText = "Booked up";
        }
    }

    addBooking() {
        this.bookingsService.create().subscribe(booking => {
            this.router.navigate(['main/edit-booking', booking.id], { queryParams: { fromCreate: true }});
        })
    }
    
    bookingDeleted(event){
        this.roomsAvailable = true;
        this.addButtonTooltipText = "Add booking";
    }

}
