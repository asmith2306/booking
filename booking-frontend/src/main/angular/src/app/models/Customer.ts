import {Booking} from "./Booking";

export interface Customer {
    id:string;
    email: string;
    firstName: string;
    lastName: string;
    bookings: Array<Booking>;
}