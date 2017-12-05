package com.asmith.booking.entities.embeddables;

/**
 * @author asmith
 */
public enum RoomType {
    SINGLE(99.99), DOUBLE(119.99), DELUXE(149.99), EXECUTIVE(199.99);
    private final double price;

    RoomType(double price) {
        this.price = price;
    }
}
