import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from '../../../node_modules/rxjs';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    private baseUrl = "/app/login";

    constructor(private httpClient: HttpClient) {}

    login(email: string, password: string): Observable<Object> {
        return this.httpClient.post(this.baseUrl, {userName: email, password: password});
    }
}
