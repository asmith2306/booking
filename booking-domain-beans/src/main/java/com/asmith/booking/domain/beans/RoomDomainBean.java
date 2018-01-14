package com.asmith.booking.domain.beans;

import com.asmith.booking.entities.Room;
import java.util.List;

/**
 * @author Alan
 */
public interface RoomDomainBean extends DomainBean<Room> {

    List<String> findAllRoomTypes();

    List<Room> findAvailableRooms();

    List<String> findAvailableRoomTypes();
    
    Room next(String type);

}
