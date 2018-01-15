import {Booking} from "./Booking";
import {RoomType} from "./RoomType";

export interface Room {
    id: number;
    roomType: RoomType;
    booking: Booking;
}