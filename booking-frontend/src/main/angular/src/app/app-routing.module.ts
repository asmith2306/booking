import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BookingsDashboardComponent} from "./bookings/dashboard/bookings-dashboard.component";
import {BookingsResolver} from "./bookings/resolvers/bookings-resolver";
import {EditBookingComponent} from "./bookings/edit-booking/edit-booking.component";
import {BookingResolver} from "./bookings/resolvers/booking-resolver";
import {AllRoomTypesResolver} from "./bookings/resolvers/all-room-types-resolver";
import {AvailableRoomTypesResolver} from "./bookings/resolvers/available-room-types-resolver";
import {AvailableRoomsResolver} from "./bookings/resolvers/available-rooms-resolver";
import {AuthComponent} from "./auth/auth.component";
import {AuthGuard} from "./auth/guards/auth.guard";
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';

const routes: Routes = [
    {
        path: 'main',
        component: AuthComponent,
        canActivate: [AuthGuard],
        children: [
            {
                path: 'bookings-dashboard',
                component: BookingsDashboardComponent,
                resolve: {
                    bookings: BookingsResolver,
                    availableRooms: AvailableRoomsResolver
                }
            },
            {
                path: 'edit-booking/:id',
                component: EditBookingComponent,
                resolve: {
                    booking: BookingResolver,
                    allRoomTypes: AllRoomTypesResolver,
                    availableRoomTypes: AvailableRoomTypesResolver
                }
            }
        ]
    },
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: '', redirectTo: '/main/bookings-dashboard', pathMatch: 'full'},
    {path: '**', component: BookingsDashboardComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
