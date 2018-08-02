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
export class BookingsResolver implements Resolve<Array<Booking>> {
    constructor(private bookingService: CustomerBookingsService, private appService: AppService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Booking[]> {
        return this.bookingService.getAll(this.appService.activeCustomer.id);
    }
}
