package com.asmith.booking.dao.service;

import com.asmith.booking.entities.Booking;
import org.springframework.data.repository.CrudRepository;

/**
 * @author asmith
 */
public interface BookingRepository extends CrudRepository<Booking, Long> {}
