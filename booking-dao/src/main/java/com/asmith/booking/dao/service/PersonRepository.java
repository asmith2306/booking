package com.asmith.booking.dao.service;

import com.asmith.booking.entities.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author asmith
 */
public interface PersonRepository extends CrudRepository<Person, Long> {}
