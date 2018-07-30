import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from '../../../node_modules/rxjs';

@Injectable({
    providedIn: 'root'
})
export class AppService {

    private baseUrl: string = "/app";
    private authUrl: string = this.baseUrl + "/auth";
    private logoutUrl: string = this.baseUrl + "/logout";

    constructor(private http: HttpClient) {}

    checkAuthorisation(): Observable<any> {
        return this.http.get(this.authUrl);
    }

    getUser(): Observable<any> {
        return this.http.get(this.authUrl + "/user");
    }

    logout() {
        return this.http.get(this.logoutUrl);
    }
}
