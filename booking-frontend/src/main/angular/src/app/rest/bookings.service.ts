import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Booking} from "../models/Booking";
import {Observable} from "rxjs/Observable";

@Injectable()
export class BookingsService {

    private baseUrl: string = "/api/bookings";

    constructor(private http: HttpClient) {}

    create(): Observable<Booking> {
        return this.http.post<Booking>(this.baseUrl + "/", new Booking());
    }

    get(id: string): Observable<Booking> {
        return this.http.get<Booking>(this.baseUrl + "/" + id);
    }

    getAll(): Observable<Booking[]> {
        return this.http.get<Array<Booking>>(this.baseUrl);
    }

    update(booking: Booking): Observable<Booking> {
        return this.http.put<Booking>(this.baseUrl + "/" + booking.id, booking);
    }

    delete(id: string): Observable<Object> {
        return this.http.delete(this.baseUrl + "/" + id);
    }


}

