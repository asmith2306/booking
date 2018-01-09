package com.asmith.booking.domain.beans;

import com.asmith.booking.dao.service.BookingRepository;
import com.asmith.booking.dao.service.RoomRepository;
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
public class BookingDomainBean implements DomainBean<Booking> {

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
        return bookingRepo.findOne(Long.valueOf(id));
    }

    @Override
    public Booking update(String id, Booking booking) {
        Booking bookingToUpdate = bookingRepo.findOne(Long.valueOf(id));

        bookingToUpdate.setCheckInDate(booking.getCheckInDate());
        bookingToUpdate.setCheckOutDate(booking.getCheckOutDate());
        bookingToUpdate.setNumberOfAdults(booking.getNumberOfAdults());
        bookingToUpdate.setNumberOfChildren(booking.getNumberOfChildren());

        List<Room> roomsToUpdate = new ArrayList<>();

        if (null != booking.getRooms()) {
            for (Room r : booking.getRooms()) {
                Room room = roomRepo.findOne(r.getId());
                room.setBooking(bookingToUpdate);
                roomsToUpdate.add(room);
                roomRepo.save(room);
            }
            bookingToUpdate.setRooms(roomsToUpdate);
        }

        return bookingRepo.save(bookingToUpdate);
    }

    /**
     * All fields in the incoming booking should be null except for the field to
     * be patched
     *
     * @param id
     * @param booking
     * @return
     */
    @Override
    public Booking patch(String id, Booking booking) {
        Booking bookingToPatch = bookingRepo.findOne(Long.valueOf(id));

        bookingToPatch = patchBooking(bookingToPatch, booking);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        Booking bookingToDelete = bookingRepo.findOne(Long.valueOf(id));
        for (Room r : bookingToDelete.getRooms()) {
            Room room = roomRepo.findOne(r.getId());
            room.setBooking(null);
            roomRepo.save(room);
        }
        bookingRepo.delete(bookingToDelete);
    }

    private Booking patchBooking(Booking oldBooking, Booking newBooking) {
        return null;
    }

}
