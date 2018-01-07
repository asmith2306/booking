import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Booking} from "../models/Booking";
import {Observable} from "rxjs/Observable";

@Injectable()
export class BookingsService {

    private baseUrl: string = "/api/bookings";

    constructor(private http: HttpClient) {}

    getAll(): Observable<Booking[]> {
        return this.http.get<Array<Booking>>(this.baseUrl);
    }

    get(id: string): Observable<Booking> {
        return this.http.get<Booking>(this.baseUrl + "/" + id);
    }


    create(): Observable<Booking> {
        return this.http.post<Booking>(this.baseUrl + "/", new Booking());
    }

    delete(id: string): Observable<Object> {
        return this.http.delete(this.baseUrl + "/" + id);
    }


}

