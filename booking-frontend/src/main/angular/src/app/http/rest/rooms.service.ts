import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {Room} from "../../models/Room";

@Injectable()
export class RoomsService {

    private baseUrl: string = "/app/api/rooms";

    constructor(private http: HttpClient) {}

    getAllRoomTypes(): Observable<Array<string>> {
        return this.http.get<Array<string>>(this.baseUrl + "/types");
    }

    getAllAvailableRooms(): Observable<Array<Room>> {
        return this.http.get<Array<Room>>(this.baseUrl + "/available");
    }
    getAllAvailableRoomTypes(): Observable<Array<string>> {
        return this.http.get<Array<string>>(this.baseUrl + "/availableTypes");
    }

    getNext(roomType: string): Observable<Room> {
        return this.http.get<Room>(this.baseUrl + "/next?type=" + roomType);
    }

}
