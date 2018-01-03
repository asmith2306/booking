import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BookingsDashboardComponent} from "./bookings-dashboard/bookings-dashboard.component";

const routes: Routes = [
    {path: 'bookings-dashboard', component: BookingsDashboardComponent},
    {path: '', redirectTo: '/bookings-dashboard', pathMatch: 'full'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
