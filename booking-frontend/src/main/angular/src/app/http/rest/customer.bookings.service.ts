import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Booking} from "../../models/Booking";
import {Observable} from "rxjs/Observable";

@Injectable()
export class CustomerBookingsService {

    private customerUriSegment = "{customerId}";
    private baseUrl = "/app/api/customers/" + this.customerUriSegment + "/bookings";

    constructor(private http: HttpClient) {}

    create(customerId: string): Observable<Booking> {
        return this.http.post<Booking>(this.buildUri(customerId) + "/",{});
    }

    get(customerId: string, bookingId: string): Observable<Booking> {
        return this.http.get<Booking>(this.buildUri(customerId) + "/" + bookingId);
    }

    getAll(customerId: string): Observable<Booking[]> {
        let uri = this.baseUrl.replace(this.customerUriSegment, customerId);
        return this.http.get<Array<Booking>>(this.buildUri(customerId));
    }

    update(customerId: string, booking: Booking): Observable<Booking> {
        let uri = this.baseUrl.replace(this.customerUriSegment, customerId);
        return this.http.put<Booking>(this.buildUri(customerId) + "/" + booking.id, booking);
    }

    delete(customerId: string, bookingId: string): Observable<Object> {
        return this.http.delete(this.buildUri(customerId) + "/" + bookingId);
    }

    private buildUri(customerId: string): string {
        return this.baseUrl.replace(this.customerUriSegment, customerId);;
    }

}

