import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {HttpErrorResponse} from '../../../node_modules/@angular/common/http';
import {RegistrationDetails} from '../models/RegistrationDetails';
import {RegistrationService} from '../http/rest/registration.service';
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
    }

    onRegister() {
        this.registrationService.register(this.registrationDetails).subscribe(res => {
            this.snackBar.open("Registration complete", null, {
                duration: 2000,
            });
            this.router.navigate(["login"]);
        }, (err: HttpErrorResponse) => {
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
