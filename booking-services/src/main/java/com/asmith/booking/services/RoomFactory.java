package com.asmith.booking.services;

import com.asmith.booking.entities.Room;

/**
 * @author Alan
 */
public interface RoomFactory {

    Room getRoom(String type);

}
