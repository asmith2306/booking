import {Component, OnInit} from '@angular/core';
import {AppService} from '../rest/app.service';
import {Router} from '../../../node_modules/@angular/router';
import {CustomerDetails} from '../models/CustomerDetails';

@Component({
    selector: 'app-auth',
    templateUrl: './auth.component.html',
    styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

    user: string = "";
    haveUser: boolean;

    constructor(private appService: AppService, private router: Router) {}

    ngOnInit() {
        this.appService.getUser().subscribe((res: CustomerDetails) => {
            this.user = res.firstName + " " + res.lastName;
            this.haveUser = true;
        });
    }

    logout() {
        this.appService.logout().subscribe(res => {
            this.router.navigate(["login"]);
        });
    }
}
