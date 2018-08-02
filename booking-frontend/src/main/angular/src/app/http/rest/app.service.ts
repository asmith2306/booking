import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from '../../../../node_modules/rxjs';
import {Subject} from "rxjs/Rx";
import {Customer} from "../../models/Customer";

@Injectable({
    providedIn: 'root'
})
export class AppService {

    private baseUrl: string = "/app";
    private authUrl: string = this.baseUrl + "/auth";
    private logoutUrl: string = this.baseUrl + "/logout";

    activeCustomer: Customer;

    constructor(private http: HttpClient) {}

    checkAuthorisation(): Observable<any> {
        return this.http.get(this.authUrl);
    }

    logout() {
        return this.http.get(this.logoutUrl);
    }
}
