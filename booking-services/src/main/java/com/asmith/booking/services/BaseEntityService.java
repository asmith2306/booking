package com.asmith.booking.services;

import java.util.List;

/**
 * The base contract for a service that implements business logic on an entity
 * At a minimum it will provide CRUD operations
 * @author asmith
 * @param <T>
 */
public interface BaseEntityService<T> {

    T create();

    T read(String id);

    T update(String id, T object);

    void delete(String id);
    
    List<T> findAll();
    
}
