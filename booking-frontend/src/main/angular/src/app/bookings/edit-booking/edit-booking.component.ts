import {Component, OnInit} from '@angular/core';
import {Booking} from "../../models/Booking";
import {CustomerBookingsService} from "../../http/rest/customer.bookings.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Room} from "../../models/Room";
import {RoomsService} from "../../http/rest/rooms.service";
import {AppService} from "../../http/rest/app.service";
import {MatDialog} from '@angular/material';
import {RoomDescriptionDialogComponent} from './room-description-dialog/room-description-dialog.component';
import {RoomType} from '../../models/RoomType';

@Component({
    selector: 'app-edit-booking',
    templateUrl: './edit-booking.component.html',
    styleUrls: ['./edit-booking.component.scss']
})
export class EditBookingComponent implements OnInit {

    booking: Booking;
    allRoomTypes: Array<RoomType> = new Array<RoomType>();
    availableRooms: Array<Room> = new Array<Room>();
    selectedRoomType: RoomType = new RoomType();
    fromCreate: boolean;

    constructor(private route: ActivatedRoute, private router: Router,
        private bookingsService: CustomerBookingsService, private snackBar: MatSnackBar,
        private roomsService: RoomsService, private appService: AppService,
        private dialog: MatDialog) {}

    ngOnInit() {
        this.booking = this.route.snapshot.data["booking"]
        this.fromCreate = JSON.parse(this.route.snapshot.queryParamMap.get("fromCreate"));
        if (this.bookingHasRooms(this.booking)) {
            this.selectedRoomType = this.booking.rooms[0].roomType;
        }

        this.allRoomTypes = this.route.snapshot.data["allRoomTypes"]
        this.availableRooms = this.route.snapshot.data["availableRooms"]
    }

    cancelEdit() {
        if (this.fromCreate) {
            this.bookingsService.delete(this.appService.activeCustomer.id, this.booking.id.toString()).subscribe(() => {
                this.snackBar.open("Booking cancelled", "", {duration: 2000});
            });
        }
        this.router.navigate(['/']);
    }

    submitDisabled() {
        let disabled = this.booking.checkInDate == null ||
            this.booking.checkOutDate == null ||
            (this.booking.numberOfAdults == null || this.booking.numberOfAdults.length < 1)
            || this.selectedRoomType.name == null;

        if (!disabled) {
            disabled = this.booking.checkInDate > this.booking.checkOutDate
        }
        return disabled;
    }

    onSubmit() {
        this.roomsService.getNext(this.selectedRoomType.name).subscribe((nextRoom: Room) => {
            this.booking.rooms = new Array<Room>();
            this.booking.rooms.push(nextRoom);

            this.bookingsService.update(this.appService.activeCustomer.id, this.booking).subscribe(booking => {
                // show message and back to dashboard on success
                this.snackBar.open("Booking saved", '', {'duration': 2000});
                this.router.navigate(['/']);
            });
        });

    }

    checkUnavailable(roomType: RoomType): boolean {
        return !this.availableRooms.some(room => room.roomType.name === roomType.name);
    }

    openRoomDescDialog() {
        this.dialog.open(RoomDescriptionDialogComponent, {data: {rooms: this.allRoomTypes}});
    }

    private bookingHasRooms(booking: Booking): boolean {
        return booking.rooms.length > 0;
    }
}