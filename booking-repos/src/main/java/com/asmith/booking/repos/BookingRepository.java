package com.asmith.booking.repos;

import com.asmith.booking.entities.Booking;
import org.springframework.data.repository.CrudRepository;

/**
 * @author asmith
 */
public interface BookingRepository extends CrudRepository<Booking, Long> {}
