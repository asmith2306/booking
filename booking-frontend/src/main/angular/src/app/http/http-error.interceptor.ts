import {Injectable} from "@angular/core";
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpResponse} from "@angular/common/http";
import {HttpErrorHandler} from "./http-error-handler";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

    private httpErrorHandler: HttpErrorHandler = new HttpErrorHandler();

    constructor(private router: Router, private snackBar: MatSnackBar) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).do((event: HttpEvent<any>) => {
            if (event instanceof HttpResponse) {
                // do stuff with response if required
            }
        }, (err: any) => {
            if (err instanceof HttpErrorResponse) {
                this.httpErrorHandler.handleError(this.router, err, this.snackBar);
            }
        });
    }

}