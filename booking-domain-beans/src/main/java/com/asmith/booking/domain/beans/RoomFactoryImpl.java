package com.asmith.booking.domain.beans;

import com.asmith.booking.entities.Room;
import com.asmith.booking.entities.embeddables.RoomType;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asmith
 */
@Service
public class RoomFactoryImpl implements RoomFactory {

    @Autowired
    RoomDomainBean roomBean;

    @Override
    public Room getRoom(String type) {
        List<Room> availableRooms = roomBean.findAvailableRooms();
        Optional<Room> nextType = availableRooms.stream().filter(room -> room.getRoomType().toString().equals(type)).findFirst();

        if (nextType.isPresent()) {
            return nextType.get();
        }

        return null;
    }

}
