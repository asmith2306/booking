import {Room} from "./Room";

export class Booking {
    id: number;
    checkInDate;
    checkOutDate;
    rooms: Array<Room>;
    numberOfAdults: number;
    numberOfChildren: number;
}

