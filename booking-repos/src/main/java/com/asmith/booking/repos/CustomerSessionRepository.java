package com.asmith.booking.repos;

import com.asmith.booking.entities.CustomerSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author asmith
 */
@Repository
public interface CustomerSessionRepository extends CrudRepository<CustomerSession, Long> {
}
