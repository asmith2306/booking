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
        return roomRepo.findOne(Long.valueOf(id));
    }

    @Override
    public Room update(String id, Room room) {
        Room roomToUpdate = roomRepo.findOne(Long.valueOf(id));

        roomToUpdate = room;

        return roomRepo.save(roomToUpdate);
    }

    /**
     * All fields in the incoming room should be null except for the field to be
     * patched
     *
     * @param id
     * @param room
     * @return
     */
    @Override
    public Room patch(String id, Room room) {
        Room roomToPatch = roomRepo.findOne(Long.valueOf(id));

        roomToPatch = patchRoom(roomToPatch, room);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        roomRepo.delete(Long.valueOf(id));
    }

    private Room patchRoom(Room oldRoom, Room newRoom) {
        return null;
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
