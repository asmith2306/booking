import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './/app-routing.module';
import {BookingsDashboardComponent} from './bookings-dashboard/bookings-dashboard.component';
import {BookingsListComponent} from "./bookings-dashboard/bookings-list/bookings-list.component";
import {CustomMaterialModule} from "./custom-material.module";
import {HttpClientModule} from "@angular/common/http";
import {BookingsService} from "./rest/bookings.service";

@NgModule({
    declarations: [
        AppComponent,
        BookingsDashboardComponent,
        BookingsListComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
        AppRoutingModule,
        CustomMaterialModule,
        HttpClientModule
    ],
    providers: [BookingsService],
    bootstrap: [AppComponent]
})
export class AppModule {}
