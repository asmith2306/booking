package com.asmith.booking.domain.beans;

import com.asmith.booking.entities.Room;

/**
 * @author Alan
 */
public interface RoomFactory {

    Room getRoom(String type);

}
