import {Component, OnInit} from '@angular/core';
import {Booking} from "../../models/Booking";
import {BookingsService} from "../../rest/bookings.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Room} from "../../models/Room";
import {RoomsService} from "../../rest/rooms.service";

@Component({
    selector: 'app-edit-booking',
    templateUrl: './edit-booking.component.html',
    styleUrls: ['./edit-booking.component.scss']
})
export class EditBookingComponent implements OnInit {

    booking: Booking;
    allRoomTypes: Array<string> = new Array<string>();
    availableRoomTypes: Array<string> = new Array<string>();
    selectedRoomType: string = "";

    constructor(private route: ActivatedRoute, private router: Router,
        private bookingsService: BookingsService, private snackBar: MatSnackBar,
        private roomsService: RoomsService) {}

    ngOnInit() {
        this.booking = this.route.snapshot.data["booking"]
        if (this.bookingHasRooms(this.booking)) {
            this.selectedRoomType = this.booking.rooms[0].roomType.name;
        }

        this.allRoomTypes = this.route.snapshot.data["allRoomTypes"]
        this.availableRoomTypes = this.route.snapshot.data["availableRoomTypes"]
    }

    private bookingHasRooms(booking: Booking): boolean {
        return booking.rooms.length > 0;
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
        this.roomsService.getNext(this.selectedRoomType).subscribe((nextRoom: Room) => {
            if (this.booking.rooms.length == 0 && nextRoom) {
                this.booking.rooms = new Array<Room>();
                this.booking.rooms.push(nextRoom);
            }

            this.bookingsService.update(this.booking).subscribe(booking => {
                // show message and back to dashboard on success
                this.snackBar.open("Booking saved", '', {'duration': 2000});
                this.router.navigate(['/']);
            });
        });

    }

    checkUnavailable(roomType: string) {
        return !this.availableRoomTypes.includes(roomType);
    }


}