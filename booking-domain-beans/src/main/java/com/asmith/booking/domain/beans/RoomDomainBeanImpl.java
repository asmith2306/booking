package com.asmith.booking.domain.beans;

import com.asmith.booking.dao.service.RoomRepository;
import com.asmith.booking.entities.Room;
import com.asmith.booking.entities.embeddables.RoomType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asmith
 */
@Service
public class RoomDomainBeanImpl implements RoomDomainBean {

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    RoomFactory roomFactory;

    @Override
    public Room create() {
        return roomRepo.save(new Room());
    }

    @Override
    public List<Room> findAll() {
        // return all rooms
        List<Room> allRooms = new ArrayList<>();
        roomRepo.findAll().iterator().forEachRemaining(allRooms::add);
        return allRooms;
    }

    @Override
    public List<String> findAllRoomTypes() {
        return roomRepo.findAllRoomTypes();
    }

    @Override
    public List<Room> findAvailableRooms() {
        return roomRepo.findAvailableRooms();
    }

    @Override
    public List<String> findAvailableRoomTypes() {
        List<Room> allAvailableRooms = this.findAvailableRooms();
        Set<String> availableRoomTypes = new HashSet<>();

        allAvailableRooms.forEach((r) -> {
            availableRoomTypes.add(r.getRoomType().toString());
        });
        return new ArrayList<>(availableRoomTypes);
    }

    @Override
    public Room find(String id) {
        return roomRepo.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public Room update(String id, Room room) {
        Room roomToUpdate = roomRepo.findById(Long.valueOf(id)).orElse(null);

        roomToUpdate = room;

        return roomRepo.save(roomToUpdate);
    }

    @Override
    public void delete(String id) {
        roomRepo.deleteById(Long.valueOf(id));
    }

    /**
     * Create 10 rooms on app start
     */
    @PostConstruct
    public void init() {
        Room room;
        List<RoomType> roomTypes = Collections.unmodifiableList(Arrays.asList(RoomType.values()));
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            room = new Room();
            room.setRoomType(roomTypes.get(random.nextInt(roomTypes.size())));
            roomRepo.save(room);
        }
    }

    @Override
    public Room next(String type) {
        return roomFactory.getRoom(type);
    }
}
