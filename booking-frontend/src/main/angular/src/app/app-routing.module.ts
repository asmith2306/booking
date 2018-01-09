import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BookingsDashboardComponent} from "./bookings/dashboard/bookings-dashboard.component";
import {BookingsResolver} from "./bookings/bookings-resolver";
import {EditBookingComponent} from "./bookings/edit-booking/edit-booking.component";
import {BookingResolver} from "./bookings/booking-resolver";

const routes: Routes = [
    {
        path: 'bookings-dashboard',
        component: BookingsDashboardComponent,
        resolve: {
            bookings: BookingsResolver
        }
    },
    {
        path: 'edit-booking/:id',
        component: EditBookingComponent,
        resolve: {
            booking: BookingResolver
        }
    },
    {path: '', redirectTo: '/bookings-dashboard', pathMatch: 'full'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
