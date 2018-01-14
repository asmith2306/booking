import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class RoomsService {

    private baseUrl: string = "/api/rooms";

    constructor(private http: HttpClient) {}

    getAllRoomTypes(): Observable<Array<string>> {
        return this.http.get<Array<string>>(this.baseUrl + "/types");
    }

    getAllAvailableRoomTypes(): Observable<Array<string>> {
        return this.http.get<Array<string>>(this.baseUrl + "/availableTypes");
    }

}
