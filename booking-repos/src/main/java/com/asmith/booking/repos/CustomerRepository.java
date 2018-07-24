package com.asmith.booking.repos;

import com.asmith.booking.entities.Customer;
import com.asmith.booking.entities.CustomerSession;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author asmith
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByEmail(String email);

    List<Customer> findByCustomerSession(CustomerSession customerSession);

}
