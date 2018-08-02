import {Injectable} from "@angular/core";
import {Resolve} from "@angular/router";
import {Router} from "@angular/router";
import {ActivatedRouteSnapshot} from "@angular/router";
import {RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {Customer} from "../models/Customer";
import {AppService} from "../http/rest/app.service";

@Injectable()
export class AuthResolver implements Resolve<Customer> {

    constructor(private appService: AppService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Customer | Observable<Customer> | Promise<Customer> {
        return this.appService.activeCustomer;
    }
} 