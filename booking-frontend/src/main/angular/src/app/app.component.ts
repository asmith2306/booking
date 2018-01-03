import {Component, OnInit} from '@angular/core';
import {Booking} from "./models/Booking";
import {BookingsService} from "./rest/bookings.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
    bookings: Array<Booking>;

    constructor(private bookingsService: BookingsService) {
        this.bookingsService.getAll();
    }
    
    ngOnInit(){
        
    }
}
