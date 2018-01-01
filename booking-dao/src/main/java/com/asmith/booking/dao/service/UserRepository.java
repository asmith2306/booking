package com.asmith.booking.dao.service;

import com.asmith.booking.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author asmith
 */
public interface UserRepository extends CrudRepository<User, Long> {}
