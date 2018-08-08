package com.asmith.booking.services;

/**
 * @author Alan
 * @param <T> the entity type to get an instance of
 * @param <V> the type to create it by e.g a room type, a car color, etc.
 */
public interface EntityFactory<T,V> {

    T getInstance(V creationType);

}
