import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginService} from '../rest/login.service';
import {Router} from "@angular/router";
import {HttpErrorResponse} from '../../../node_modules/@angular/common/http';
import {MatDialog} from '../../../node_modules/@angular/material';
import {RegistrationDetails} from '../models/RegistrationDetails';
import {RegistrationService} from '../rest/registration.service';
import {NgForm} from '../../../node_modules/@angular/forms';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    @ViewChild('registrationForm')
    public registrationForm: NgForm;

    registrationDetails: RegistrationDetails;

    constructor(private registrationService: RegistrationService, private router: Router, private snackBar: MatSnackBar) {}

    ngOnInit() {
        this.registrationDetails = new RegistrationDetails();
        console.log(this.registrationForm)
    }

    onRegister() {
        console.log("registering")
        this.registrationService.register(this.registrationDetails).subscribe(res => {
            console.log(res)
            this.router.navigate(["login"]);
        }, (err: HttpErrorResponse) => {
            console.log(err)
            this.snackBar.open(err.error, null, {
                duration: 5000,
            });
        })
    }

    registrationDisabled(): boolean {
        return !this.registrationForm.valid;
    }

    openRegisterDialog() {
        this.router.navigate(["register"]);
    }

}
