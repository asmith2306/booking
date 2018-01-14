package com.asmith.booking.dao.service;

import com.asmith.booking.entities.Room;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author asmith
 */
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query(value = "SELECT DISTINCT ROOM_TYPE FROM ROOM", nativeQuery = true)
    public List<String> findAllRoomTypes();

    @Query(value = "SELECT * FROM ROOM where BOOKING_ID IS null", nativeQuery = true)
    public List<Room> findAvailableRooms();
}
