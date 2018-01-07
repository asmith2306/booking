import {Component, OnInit, Input} from '@angular/core';
import {MatTableDataSource} from "@angular/material";
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

    constructor(private router: Router, private bookingService: BookingsService) {}

    ngOnInit() {
        this.dataSource = new MatTableDataSource<Booking>(this.bookings);
    }

    onClickEditBooking(id: string) {
        this.router.navigate(['edit-booking', id])
    }

    onClickDeleteBooking(id: string) {
        this.bookingService.delete(id).subscribe(() => {
            this.bookingService.getAll().subscribe(bookings => {
                this.dataSource = new MatTableDataSource<Booking>(bookings);
            })
        });
    }

}
