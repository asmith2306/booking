import {Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import {MatTableDataSource, MatSnackBar} from "@angular/material";
import {Booking} from "../../models/Booking";
import {Router} from "@angular/router";
import {BookingsService} from "../../rest/bookings.service";

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

    constructor(private router: Router, private bookingService: BookingsService, private snackBar: MatSnackBar) {}

    ngOnInit() {
        this.dataSource = new MatTableDataSource<Booking>(this.bookings);
    }

    onClickEditBooking(id: string) {
        this.router.navigate(['edit-booking', id]);
    }

    onClickDeleteBooking(id: string) {
        this.bookingService.delete(id).subscribe(() => {
            this.snackBar.open("Booking deleted", "", {duration: 2000});
            this.bookingService.getAll().subscribe(bookings => {
                this.dataSource = new MatTableDataSource<Booking>(bookings);
                this.bookingDeleted.emit(true);
            })
        });
    }

}
