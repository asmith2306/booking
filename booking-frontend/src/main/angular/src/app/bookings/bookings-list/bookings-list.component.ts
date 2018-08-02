import {Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import {MatTableDataSource, MatSnackBar} from "@angular/material";
import {Booking} from "../../models/Booking";
import {Router} from "@angular/router";
import {CustomerBookingsService} from "../../http/rest/customer.bookings.service";
import {AppService} from "../../http/rest/app.service";

@Component({
    selector: 'app-bookings-list',
    templateUrl: './bookings-list.component.html',
    styleUrls: ['./bookings-list.component.css']
})
export class BookingsListComponent implements OnInit {

    @Input()
    bookings: Array<Booking>;

    displayedColumns = ['checkInDate', 'checkOutDate', 'numberOfAdults', 'numberOfChildren', 'edit', 'delete'];
    dataSource: MatTableDataSource<Booking>;

    @Output()
    bookingDeleted = new EventEmitter();

    constructor(private router: Router, private bookingService: CustomerBookingsService, private snackBar: MatSnackBar,
        private appService: AppService) {}

    ngOnInit() {
        this.dataSource = new MatTableDataSource<Booking>(this.bookings);
    }

    onClickEditBooking(id: string) {
        this.router.navigate(['main/edit-booking', id]);
    }

    onClickDeleteBooking(id: string) {
        this.bookingService.delete(this.appService.activeCustomer.id, id).subscribe(() => {
            this.snackBar.open("Booking deleted", "", {duration: 2000});
            this.bookingService.getAll(this.appService.activeCustomer.id).subscribe(bookings => {
                this.dataSource = new MatTableDataSource<Booking>(bookings);
                this.bookingDeleted.emit(true);
            })
        });
    }

}
