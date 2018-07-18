package com.asmith.booking.repos;

import com.asmith.booking.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author asmith
 */
public interface UserRepository extends CrudRepository<User, Long> {}
