import {Component, OnInit} from '@angular/core';
import {AppService} from '../http/rest/app.service';
import {Router} from '../../../node_modules/@angular/router';
import {Customer} from '../models/Customer';

@Component({
    selector: 'app-auth',
    templateUrl: './auth.component.html',
    styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

    activeCustomer: Customer;

    constructor(private appService: AppService, private router: Router) {}

    ngOnInit() {
        this.activeCustomer = this.appService.activeCustomer;
    }

    logout() {
        this.appService.logout().subscribe(res => {
            this.router.navigate(["login"]);
        });
    }
}
