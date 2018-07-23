package com.asmith.booking.services;

import com.asmith.booking.repos.BookingRepository;
import com.asmith.booking.repos.RoomRepository;
import com.asmith.booking.entities.Booking;
import com.asmith.booking.entities.Room;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asmith
 */
@Service
public class BookingService implements DomainService<Booking> {

    @Autowired
    BookingRepository bookingRepo;
    @Autowired
    RoomRepository roomRepo;

    @Override
    public Booking create() {
        return bookingRepo.save(new Booking());
    }

    @Override
    public List<Booking> findAll() {
        // return all bookings
        List<Booking> allBookings = new ArrayList<>();
        bookingRepo.findAll().iterator().forEachRemaining(allBookings::add);
        return allBookings;
    }

    @Override
    public Booking find(String id) {
        return bookingRepo.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public Booking update(String id, Booking booking) {
        Booking bookingToUpdate = bookingRepo.findById(Long.valueOf(id)).orElse(null);

        bookingToUpdate.setCheckInDate(booking.getCheckInDate());
        bookingToUpdate.setCheckOutDate(booking.getCheckOutDate());
        bookingToUpdate.setNumberOfAdults(booking.getNumberOfAdults());
        bookingToUpdate.setNumberOfChildren(booking.getNumberOfChildren());

        List<Room> roomsToUpdate = new ArrayList<>();

        if (null != booking.getRooms()) {
            Room previousRoom = roomRepo.findRoomByBookingId(Long.valueOf(id));
            if (null != previousRoom) {
                previousRoom.setBooking(null);
                roomRepo.save(previousRoom);
            }
            for (Room r : booking.getRooms()) {
                Room room = roomRepo.findById(r.getId()).orElse(null);
                room.setBooking(bookingToUpdate);
                roomsToUpdate.add(room);
                roomRepo.save(room);
            }
            bookingToUpdate.setRooms(roomsToUpdate);
        }

        return bookingRepo.save(bookingToUpdate);
    }

    @Override
    public void delete(String id) {
        Booking bookingToDelete = bookingRepo.findById(Long.valueOf(id)).orElse(null);
        for (Room r : bookingToDelete.getRooms()) {
            Room room = roomRepo.findById(r.getId()).orElse(null);
            room.setBooking(null);
            roomRepo.save(room);
        }
        bookingRepo.delete(bookingToDelete);
    }

}
