package com.asmith.booking.repos;

import com.asmith.booking.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author asmith
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
