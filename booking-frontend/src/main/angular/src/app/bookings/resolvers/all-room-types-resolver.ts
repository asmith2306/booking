import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {
    Router, Resolve, RouterStateSnapshot,
    ActivatedRouteSnapshot
} from '@angular/router';
import {RoomsService} from "../../http/rest/rooms.service";
import {RoomType} from '../../models/RoomType';

@Injectable()
export class AllRoomTypesResolver implements Resolve<Array<RoomType>> {
    constructor(private roomService: RoomsService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Array<RoomType>> {
        // api will return an empty array if there are no bookings so safe to
        // to not do a check for them
        return this.roomService.getAllRoomTypes();
    }
}

