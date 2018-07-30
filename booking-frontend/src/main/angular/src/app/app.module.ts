import {BrowserModule} from '@angular/platform-browser';
import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './/app-routing.module';
import {CustomMaterialModule} from "./custom-material.module";
import {HttpClientModule} from "@angular/common/http";
import {BookingsService} from "./rest/bookings.service";
import {ClickableIndicatorDirective} from "./directives/clickable-indicator.directive";
import {BookingsDashboardComponent} from "./bookings/dashboard/bookings-dashboard.component";
import {BookingsListComponent} from "./bookings/bookings-list/bookings-list.component";
import {BookingsResolver} from "./bookings/resolvers/bookings-resolver";
import {EditBookingComponent} from './bookings/edit-booking/edit-booking.component';
import {FormsModule} from "@angular/forms";
import {BookingResolver} from "./bookings/resolvers/booking-resolver";
import {AllRoomTypesResolver} from "./bookings/resolvers/all-room-types-resolver";
import {AvailableRoomTypesResolver} from "./bookings/resolvers/available-room-types-resolver";
import {RoomsService} from "./rest/rooms.service";
import {AvailableRoomsResolver} from "./bookings/resolvers/available-rooms-resolver";
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {AuthComponent} from './auth/auth.component';
import {AuthGuard} from "./auth/guards/auth.guard";
import {AppService} from "./rest/app.service";
import {LoginService} from "./rest/login.service";

@NgModule({
    declarations: [
        AppComponent,
        BookingsDashboardComponent,
        BookingsListComponent,
        ClickableIndicatorDirective,
        EditBookingComponent,
        RegisterComponent,
        LoginComponent,
        AuthComponent
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
    providers: [BookingsService, BookingsResolver, BookingResolver, RoomsService,
        AllRoomTypesResolver, AvailableRoomTypesResolver, AvailableRoomsResolver, AuthGuard, AppService, LoginService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    bootstrap: [AppComponent]
})
export class AppModule {}
