package com.asmith.booking.services;

import com.asmith.booking.entities.Room;
import com.asmith.booking.entities.embeddables.RoomType;
import java.util.List;

/**
 * @author Alan
 */
public interface RoomDomainBean extends DomainService<Room> {

    List<RoomType> findAllRoomTypes();

    List<Room> findAvailableRooms();

    List<RoomType> findAvailableRoomTypes();
    
    Room next(String type);

}
