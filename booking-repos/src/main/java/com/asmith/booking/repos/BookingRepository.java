package com.asmith.booking.repos;

import com.asmith.booking.entities.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author asmith
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {}
