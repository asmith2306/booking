import {Injectable} from '@angular/core';
import {RegistrationDetails} from '../../models/RegistrationDetails';
import {HttpClient} from '../../../../node_modules/@angular/common/http';
import {Observable} from '../../../../node_modules/rxjs';

@Injectable({
    providedIn: 'root'
})
export class RegistrationService {

    private baseUrl = "/app/register"

    constructor(private httpClient: HttpClient) {}

    register(registrationDetails: RegistrationDetails): Observable<any> {
        return this.httpClient.post(this.baseUrl, registrationDetails);
    }
}
