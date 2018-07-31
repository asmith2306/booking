import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AppService} from '../../rest/app.service';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private appService: AppService, private router: Router) {

    }

    canActivate() {
        return this.appService.checkAuthorisation().map(res => {
            if (res) {
                return true;
            }
        }).catch(() => {
            this.router.navigate(['/login']);
            return Observable.of(false);
        });
    }
}
