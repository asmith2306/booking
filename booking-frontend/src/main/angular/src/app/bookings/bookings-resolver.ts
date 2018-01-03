import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {
    Router, Resolve, RouterStateSnapshot,
    ActivatedRouteSnapshot
} from '@angular/router';
import {BookingsService} from "../rest/bookings.service";
import {Booking} from "../models/Booking";

@Injectable()
export class BookingsResolver implements Resolve<Array<Booking>> {
    constructor(private bookingService: BookingsService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Booking[]> {
        // api will return an empty array if there are no bookings so safe to
        // to not do a check for them
        return this.bookingService.getAll(); 
    }
}
