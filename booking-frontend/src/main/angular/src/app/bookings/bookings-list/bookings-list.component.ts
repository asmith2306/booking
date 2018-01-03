import {Component, OnInit, Input} from '@angular/core';
import {MatTableDataSource} from "@angular/material";
import {Booking} from "../../models/Booking";

@Component({
    selector: 'app-bookings-list',
    templateUrl: './bookings-list.component.html',
    styleUrls: ['./bookings-list.component.css']
})
export class BookingsListComponent implements OnInit {

    @Input()
    bookings: Array<Booking>;
    displayedColumns = ['checkInDate', 'checkOutDate', 'numberOfAdults', 'numberOfChildren', 'edit'];
    dataSource: MatTableDataSource<Booking>;

    constructor() {}

    ngOnInit() {
        console.log(this.bookings)
        this.dataSource = new MatTableDataSource<Booking>(this.bookings);
    }

    onClickEditBooking(id: string) {
        console.log(id)
    }

}

export interface Element {
    name: string;
    position: number;
    weight: number;
    symbol: string;
}

const ELEMENT_DATA: Element[] = [
    {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
    {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
    {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
    {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
    {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'}
];
