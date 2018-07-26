import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthService} from '../../rest/auth.service';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router) {

    }

    canActivate() {
        console.log('AuthGuard#canActivate called');

        return this.authService.checkAuthorisation().map(res => {
            if (res) {
                return true;
            }
        }).catch(() => {
            this.router.navigate(['/login']);
            return Observable.of(false);
        });
    }
}
