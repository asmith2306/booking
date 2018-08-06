import {BrowserModule} from '@angular/platform-browser';
import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './/app-routing.module';
import {CustomMaterialModule} from "./custom-material.module";
import {HttpClientModule} from "@angular/common/http";
import {CustomerBookingsService} from "./http/rest/customer.bookings.service";
import {ClickableIndicatorDirective} from "./directives/clickable-indicator.directive";
import {BookingsDashboardComponent} from "./bookings/dashboard/bookings-dashboard.component";
import {BookingsListComponent} from "./bookings/bookings-list/bookings-list.component";
import {BookingsResolver} from "./bookings/resolvers/bookings-resolver";
import {EditBookingComponent} from './bookings/edit-booking/edit-booking.component';
import {FormsModule} from "@angular/forms";
import {AllRoomTypesResolver} from "./bookings/resolvers/all-room-types-resolver";
import {AvailableRoomTypesResolver} from "./bookings/resolvers/available-room-types-resolver";
import {RoomsService} from "./http/rest/rooms.service";
import {AvailableRoomsResolver} from "./bookings/resolvers/available-rooms-resolver";
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {AuthComponent} from './auth/auth.component';
import {AuthGuard} from "./auth/auth.guard";
import {AuthService} from "./auth/auth.service";
import {AuthResolver} from "./auth/auth.resolve";
import {AppService} from "./http/rest/app.service";
import {LoginService} from "./http/rest/login.service";
import {BookingResolver} from "./bookings/resolvers/booking-resolver";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {HttpErrorInterceptor} from "./http/http-error.interceptor";
import { RoomDescriptionDialogComponent } from './bookings/edit-booking/room-description-dialog/room-description-dialog.component';

@NgModule({
    declarations: [
        AppComponent,
        BookingsDashboardComponent,
        BookingsListComponent,
        ClickableIndicatorDirective,
        EditBookingComponent,
        RegisterComponent,
        LoginComponent,
        AuthComponent,
        RoomDescriptionDialogComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
        AppRoutingModule,
        CustomMaterialModule,
        HttpClientModule,
        FormsModule
    ],
    providers: [CustomerBookingsService, BookingsResolver, BookingResolver, RoomsService,
        AllRoomTypesResolver, AvailableRoomTypesResolver, AvailableRoomsResolver, AuthGuard, AuthService,
        AuthResolver, AppService, LoginService, {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpErrorInterceptor,
            multi: true
        }],
    entryComponents:[RoomDescriptionDialogComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    bootstrap: [AppComponent]
})
export class AppModule {}
