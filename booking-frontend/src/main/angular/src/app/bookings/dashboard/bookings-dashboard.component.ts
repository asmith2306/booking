import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Booking} from "../../models/Booking";
import {CustomerBookingsService} from "../../http/rest/customer.bookings.service";
import {RoomsService} from "../../http/rest/rooms.service";
import {AppService} from "../../http/rest/app.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatButtonToggleChange} from "@angular/material/button-toggle";
import {ViewChild} from "@angular/core";
import {DynamicHostDirective} from "./dynamic.host.directive";
import {ComponentFactoryResolver} from "@angular/core";
import {LeftComponent} from "./left/left.component";

@Component({
    selector: 'app-bookings-dashboard',
    templateUrl: './bookings-dashboard.component.html',
    styleUrls: ['./bookings-dashboard.component.css']
})
export class BookingsDashboardComponent implements OnInit {

    @ViewChild('DynamicHostDirective')
    dynamicHost: DynamicHostDirective;

    bookings: Array<Booking>;
    roomsAvailable: boolean;
    addButtonTooltipText: string
    doEnterAnimation: boolean;
    doExitAnimation: boolean;

    constructor(private router: Router, private route: ActivatedRoute,
        private bookingsService: CustomerBookingsService, private roomsService: RoomsService,
        private appService: AppService, private snackBar: MatSnackBar,
        private componentFactoryResolver: ComponentFactoryResolver) {}

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
        
        console.log(this.dynamicHost)
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

    mouseEnter() {
        this.doEnterAnimation = true;
        this.doExitAnimation = false;
    }

    mouseLeave() {
        this.doEnterAnimation = false;
        this.doExitAnimation = true;
    }

    doChange(change: MatButtonToggleChange) {
        console.log(change.value)

        let componentFactory = this.componentFactoryResolver.resolveComponentFactory(LeftComponent);
        let viewContainerRef = this.dynamicHost.viewContainerRef;
        viewContainerRef.clear();
        let componentRef = viewContainerRef.createComponent(componentFactory);
    }
}
