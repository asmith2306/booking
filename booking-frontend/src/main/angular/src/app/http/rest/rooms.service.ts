import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {Room} from "../../models/Room";
import {RoomType} from '../../models/RoomType';

@Injectable()
export class RoomsService {

    private baseUrl: string = "/app/api/rooms";

    constructor(private http: HttpClient) {}

    getAllRoomTypes(): Observable<Array<RoomType>> {
        return this.http.get<Array<RoomType>>(this.baseUrl + "/types");
    }

    getAllAvailableRooms(): Observable<Array<Room>> {
        return this.http.get<Array<Room>>(this.baseUrl + "/available");
    }
    getAllAvailableRoomTypes(): Observable<Array<RoomType>> {
        return this.http.get<Array<RoomType>>(this.baseUrl + "/availableTypes");
    }

    getNext(roomType: string): Observable<Room> {
        return this.http.get<Room>(this.baseUrl + "/next?type=" + roomType);
    }

    getAllRooms(): Observable<Array<Room>> {
        return this.http.get<Array<Room>>(this.baseUrl);
    }

}
