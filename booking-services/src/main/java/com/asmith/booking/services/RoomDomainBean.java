package com.asmith.booking.services;

import com.asmith.booking.entities.Room;
import java.util.List;

/**
 * @author Alan
 */
public interface RoomDomainBean extends DomainService<Room> {

    List<String> findAllRoomTypes();

    List<Room> findAvailableRooms();

    List<String> findAvailableRoomTypes();
    
    Room next(String type);

}
