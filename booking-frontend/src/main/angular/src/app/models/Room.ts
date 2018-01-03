import {Booking} from "./Booking";

export class Room{
    id:number;
    roomType:RoomType;
    booking:Booking;
}

enum RoomType {
    Single,
    Double,
    Deluxe,
    Executive
}