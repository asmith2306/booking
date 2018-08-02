import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

export class HttpErrorHandler {
    handleError(router: Router, httpErrorResponse: HttpErrorResponse, snackBar: MatSnackBar) {
        switch (httpErrorResponse.status) {
            case 401:
                snackBar.open("Session expired", '', {'duration': 3000});
                router.navigate(["login"]);
                break;
        }
    }
}
