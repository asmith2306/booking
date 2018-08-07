package com.asmith.booking.services;

import com.asmith.booking.entities.Customer;
import com.asmith.booking.entities.embeddables.LoginDetails;
import com.asmith.booking.entities.embeddables.RegistrationDetails;
import com.asmith.booking.repos.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asmith
 */
@Service
public class AccessService {

    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> customerExists(String email) {
        return customerRepo.findByEmail(email);
    }

    public void registerCustomer(RegistrationDetails registrationDetails) {
        Customer newCustomer = new Customer();
        newCustomer.setEmail(registrationDetails.getEmail());
        newCustomer.setFirstName(registrationDetails.getFirstName());
        newCustomer.setLastName(registrationDetails.getLastName());

        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUserName(newCustomer.getEmail());
        loginDetails.setPassword(registrationDetails.getPassword());

        newCustomer.setLoginDetails(loginDetails);

        customerRepo.save(newCustomer);
    }

    public boolean invalidPassword(LoginDetails loginDetails) {
        Customer customer = customerRepo.findByEmail(loginDetails.getUserName()).get(0);
        return !customer.getLoginDetails().getPassword().equals(loginDetails.getPassword());
    }

}
