import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from '../../../node_modules/rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private baseUrl: string = "/app/auth";

    constructor(private http: HttpClient) {}

    checkAuthorisation(): Observable<any> {
        return this.http.get(this.baseUrl);     
    }
}
