import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginService} from '../rest/login.service';
import {Router} from "@angular/router";
import {HttpErrorResponse} from '../../../node_modules/@angular/common/http';
import {MatDialog} from '../../../node_modules/@angular/material';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    email: string = "";
    password: string = "";
    showRegistrationLink = false;
    loginError: string;

    constructor(private loginService: LoginService, private router: Router) {}

    ngOnInit() {}

    onLogin() {
        this.loginService.login(this.email, this.password).subscribe(res => {
            this.router.navigate(["main/bookings-dashboard"]);
        }, (err: HttpErrorResponse) => {
            this.showRegistrationLink = true;
            this.loginError = err.error;
        })
    }

    loginDisabled(): boolean {
        return this.email.length < 1 || this.password.length < 1;
    }
    
    openRegisterDialog(){
        this.router.navigate(["register"]);
    }

}
