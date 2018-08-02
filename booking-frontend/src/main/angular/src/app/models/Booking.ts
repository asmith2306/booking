import {Room} from "./Room";

export interface Booking {
    id: string;
    checkInDate: Date;
    checkOutDate: Date;
    rooms: Array<Room>;
    numberOfAdults: string;
    numberOfChildren: string;
}

