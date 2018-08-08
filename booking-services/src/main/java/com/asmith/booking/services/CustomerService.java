package com.asmith.booking.services;

import com.asmith.booking.entities.Booking;
import com.asmith.booking.entities.Customer;
import com.asmith.booking.entities.Room;
import com.asmith.booking.repos.BookingRepository;
import com.asmith.booking.repos.CustomerRepository;
import com.asmith.booking.repos.RoomRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asmith
 */
@Service
public class CustomerService implements BaseEntityService<Customer> {

    private final CustomerRepository customerRepository;

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Customer create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer read(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer update(String id, Customer object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ///////////////////////////////////
    // Customer booking related methods
    ///////////////////////////////////
    public Booking createBooking(String customerId) {
        Customer customer = getCustomer(customerId);

        Booking booking = bookingRepository.save(new Booking());
        customer.getBookings().add(booking);
        customerRepository.save(customer);

        return booking;
    }

    public Booking findBooking(String customerId, String bookingId) {
        Customer customer = getCustomer(customerId);
        Booking booking = customer.getBookings().stream().filter(b -> b.getId().equals(Long.valueOf(bookingId))).findFirst().get();
        return booking;
    }

    public List<Booking> findAllBookings(String customerId) {
        Customer customer = getCustomer(customerId);
        return customer.getBookings();
    }

    public Booking updateBooking(String customerId, String bookingId, Booking bookingIn) {
        Booking bookingToUpdate = bookingRepository.findById(Long.valueOf(bookingId)).orElse(null);

        bookingToUpdate.setCheckInDate(bookingIn.getCheckInDate());
        bookingToUpdate.setCheckOutDate(bookingIn.getCheckOutDate());
        bookingToUpdate.setNumberOfAdults(bookingIn.getNumberOfAdults());
        bookingToUpdate.setNumberOfChildren(bookingIn.getNumberOfChildren());

        List<Room> roomsToUpdate = new ArrayList<>();

        if (null != bookingIn.getRooms()) {
            Room previousRoom = roomRepository.findRoomByBookingId(Long.valueOf(bookingId));
            if (null != previousRoom) {
                previousRoom.setBooking(null);
                roomRepository.save(previousRoom);
            }
            for (Room r : bookingIn.getRooms()) {
                Room room = roomRepository.findById(r.getId()).orElse(null);
                room.setBooking(bookingToUpdate);
                roomsToUpdate.add(room);
                roomRepository.save(room);
            }
            bookingToUpdate.setRooms(roomsToUpdate);
        }

        return bookingRepository.save(bookingToUpdate);
    }

    public void deleteBooking(String customerId, String bookingId) {
        Customer customer = getCustomer(customerId);
        Booking bookingToDelete = bookingRepository.findById(Long.valueOf(bookingId)).orElse(null);
        customer.getBookings().remove(bookingToDelete);

        for (Room r : bookingToDelete.getRooms()) {
            Room room = roomRepository.findById(r.getId()).orElse(null);
            room.setBooking(null);
            roomRepository.save(room);
        }
        customerRepository.save(customer);
        bookingRepository.delete(bookingToDelete);
    }

    private Customer getCustomer(String customerId) {
        return customerRepository.findById(Long.valueOf(customerId)).get();
    }
}
