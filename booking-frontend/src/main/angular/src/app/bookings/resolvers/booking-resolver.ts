import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {
    Router, Resolve, RouterStateSnapshot,
    ActivatedRouteSnapshot
} from '@angular/router';
import {CustomerBookingsService} from "../../http/rest/customer.bookings.service";
import {Booking} from "../../models/Booking";
import {AppService} from "../../http/rest/app.service";

@Injectable()
export class BookingResolver implements Resolve<Booking> {
    constructor(private bookingService: CustomerBookingsService, private appService: AppService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Booking> {
        // should check for id and throw err if not in route
        return this.bookingService.get(this.appService.activeCustomer.id, route.params["id"]);
    }
}
