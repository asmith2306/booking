package com.asmith.booking.services;

import com.asmith.booking.entities.Room;
import com.asmith.booking.entities.embeddables.RoomType;
import java.util.List;

/**
 * @author Alan
 */
public interface RoomEntityService extends BaseEntityService<Room> {

    /**
     * Returns all room types in the system
     *
     * @return
     */
    List<RoomType> findAllRoomTypes();

    /**
     * Returns all available rooms in the system, i.e. rooms that aren't booked
     * up
     *
     * @return
     */
    List<Room> findAvailableRooms();

    /**
     * Returns the next available room in the system by type
     * up
     *
     * @param type
     * @return
     */
    Room next(String type);

}
