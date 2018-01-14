import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {
    Router, Resolve, RouterStateSnapshot,
    ActivatedRouteSnapshot
} from '@angular/router';
import {RoomsService} from "../../rest/rooms.service";

@Injectable()
export class AllRoomTypesResolver implements Resolve<Array<string>> {
    constructor(private roomService: RoomsService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Array<string>> {
        // api will return an empty array if there are no bookings so safe to
        // to not do a check for them
        return this.roomService.getAllRoomTypes();
    }
}

