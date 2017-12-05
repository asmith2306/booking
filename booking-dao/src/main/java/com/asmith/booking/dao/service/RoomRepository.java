package com.asmith.booking.dao.service;

import com.asmith.booking.entities.Room;
import org.springframework.data.repository.CrudRepository;

/**
 * @author asmith
 */
public interface RoomRepository extends CrudRepository<Room, Long> {}
