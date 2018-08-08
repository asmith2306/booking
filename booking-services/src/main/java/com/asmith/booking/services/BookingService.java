package com.asmith.booking.services;

import com.asmith.booking.repos.BookingRepository;
import com.asmith.booking.repos.RoomRepository;
import com.asmith.booking.entities.Booking;
import com.asmith.booking.entities.Customer;
import com.asmith.booking.entities.Room;
import com.asmith.booking.repos.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asmith
 */
@Service
public class BookingService implements BaseEntityService<Booking> {

    private static final Logger LOG = LoggerFactory.getLogger(BookingService.class.getName());

    private final BookingRepository bookingRepo;

    private final CustomerRepository customerRepository;

    private final RoomRepository roomRepo;

    private final HttpServletRequest request;

    @Autowired
    public BookingService(BookingRepository bookingRepo, CustomerRepository customerRepository, RoomRepository roomRepo, HttpServletRequest request) {
        this.bookingRepo = bookingRepo;
        this.customerRepository = customerRepository;
        this.roomRepo = roomRepo;
        this.request = request;
    }

    @Override
    public Booking create() {
        Customer customer = (Customer) request.getAttribute("customer");

        Booking booking = new Booking();
        customer.getBookings().add(booking);

        bookingRepo.save(booking);
        customerRepository.save(customer);

        return booking;
    }

    @Override
    public List<Booking> findAll() {
        // return all bookings
        List<Booking> allBookings = new ArrayList<>();
        bookingRepo.findAll().iterator().forEachRemaining(allBookings::add);
        return allBookings;
    }

    public List<Booking> findAllCustomerBookings() {
        Customer customer = (Customer) request.getAttribute("customer");

        // return all customer bookings
        List<Booking> customerBookings = new ArrayList<>();
        customerBookings.addAll(customer.getBookings());
        return customerBookings;
    }

    @Override
    public Booking read(String id) {
        return bookingRepo.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public Booking update(String id, Booking bookingIn) {
        Booking bookingToUpdate = bookingRepo.findById(Long.valueOf(id)).orElse(null);

        bookingToUpdate.setCheckInDate(bookingIn.getCheckInDate());
        bookingToUpdate.setCheckOutDate(bookingIn.getCheckOutDate());
        bookingToUpdate.setNumberOfAdults(bookingIn.getNumberOfAdults());
        bookingToUpdate.setNumberOfChildren(bookingIn.getNumberOfChildren());

        List<Room> roomsToUpdate = new ArrayList<>();

        if (null != bookingIn.getRooms()) {
            Room previousRoom = roomRepo.findRoomByBookingId(Long.valueOf(id));
            if (null != previousRoom) {
                previousRoom.setBooking(null);
                roomRepo.save(previousRoom);
            }
            for (Room r : bookingIn.getRooms()) {
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
        Customer customer = (Customer) request.getAttribute("customer");

        Booking bookingToDelete = bookingRepo.findById(Long.valueOf(id)).orElse(null);

        customer.getBookings().remove(bookingToDelete);

        for (Room r : bookingToDelete.getRooms()) {
            Room room = roomRepo.findById(r.getId()).orElse(null);
            room.setBooking(null);
            roomRepo.save(room);
        }
        customerRepository.save(customer);
        bookingRepo.delete(bookingToDelete);
    }

}
