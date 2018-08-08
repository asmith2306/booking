package com.asmith.booking.services;

import com.asmith.booking.repos.RoomRepository;
import com.asmith.booking.entities.Room;
import com.asmith.booking.entities.embeddables.RoomType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asmith
 */
@Service
public class RoomService implements RoomEntityService {

    private static final Logger LOG = Logger.getLogger(RoomService.class.getName());

    private final RoomRepository roomRepo;

    private final EntityFactory<Room, String> roomFactory;

    @Autowired
    public RoomService(RoomRepository roomRepo, EntityFactory<Room, String> roomFactory) {
        this.roomRepo = roomRepo;
        this.roomFactory = roomFactory;
    }

    /**
     * Create some rooms on app start
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
    public List<RoomType> findAllRoomTypes() {
        List<String> typeNames = roomRepo.findAllRoomTypes();
        List<RoomType> roomTypes = new ArrayList<>();

        for (String s : typeNames) {
            roomTypes.add(RoomType.valueOf(s));
        }

        return roomTypes;
    }

    @Override
    public List<Room> findAvailableRooms() {
        return roomRepo.findAvailableRooms();
    }
    
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

    @Override
    public Room next(String type) {
        return roomFactory.getInstance(type);
    }

    @Override
    public Room read(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
