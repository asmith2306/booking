package com.asmith.booking.services;

import com.asmith.booking.entities.Room;
import com.asmith.booking.repos.RoomRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asmith
 */
@Service
public class RoomFactory implements EntityFactory<Room, String> {

    private final RoomRepository roomRepo;

    @Autowired
    public RoomFactory(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public Room getInstance(String type) {
        List<Room> availableRooms = roomRepo.findAvailableRooms();
        Optional<Room> nextType = availableRooms.stream().filter(room -> room.getRoomType().name().equalsIgnoreCase(type)).findFirst();

        if (nextType.isPresent()) {
            return nextType.get();
        }

        return null;
    }

}
