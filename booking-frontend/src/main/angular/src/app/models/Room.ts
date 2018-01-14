import {Booking} from "./Booking";

export class Room{
    id:number;
    roomType:RoomType;
    booking:Booking;
}

export enum RoomType {
    Single,
    Double,
    Deluxe,
    Executive
}