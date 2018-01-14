import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {
    Router, Resolve, RouterStateSnapshot,
    ActivatedRouteSnapshot
} from '@angular/router';
import {BookingsService} from "../../rest/bookings.service";
import {Booking} from "../../models/Booking";

@Injectable()
export class BookingResolver implements Resolve<Booking> {
    constructor(private bookingService: BookingsService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Booking> {
        // should check for id and throw err if not in route
        return this.bookingService.get(route.params["id"]);
    }
}
