import {Room} from "./Room";

export class Booking {
    id: number;
    checkInDate: number; // ms
    checkOutDate: number; // ms
    rooms: Array<Room>;
    numberOfAdults: number;
    numberOfChildren: number;
}

