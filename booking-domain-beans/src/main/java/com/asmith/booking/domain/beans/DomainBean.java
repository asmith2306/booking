package com.asmith.booking.domain.beans;

import java.util.List;

/**
 * @author asmith
 */
public interface DomainBean<T> {

    T create();

    T find(String id);

    List<T> findAll();

    T update(String id, T object);

    void delete(String id);
    
}
