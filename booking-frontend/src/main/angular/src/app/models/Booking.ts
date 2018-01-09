import {Room} from "./Room";

export class Booking {
    id: number;
    checkInDate: Date; 
    checkOutDate: Date;
    rooms: Array<Room>;
    numberOfAdults: number;
    numberOfChildren: number;
}

