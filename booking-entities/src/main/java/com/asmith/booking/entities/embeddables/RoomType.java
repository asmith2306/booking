package com.asmith.booking.entities.embeddables;

import com.asmith.booking.entities.serialisers.json.RoomTypeSerialiser;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author asmith
 */
@JsonSerialize(using = RoomTypeSerialiser.class)
public enum RoomType {
    SINGLE(99.99, new String[]{"Bath towel", "Slippers", "Bathrobe", "Hairdryer"}),
    DOUBLE(119.99, new String[]{"Bath towels", "Slippers", "Bathrobes", "Hairdryer", "Iron", "Ironing board"}),
    DELUXE(149.99, new String[]{"Bath towels", "Slippers", "Bathrobes", "Hairdryer", "Iron", "Ironing board", "Sleepware", "Toiletries", "Safe"}),
    EXECUTIVE(199.99, new String[]{"Bath towels", "Slippers", "Bathrobes", "Hairdryer", "Iron", "Ironing board", "Sleepware", "Toiletries", "Safe",
        "Fan", "HDTV", "Stocked refrigerator", "Jacuzzi bath"});

    private final double price;
    private final String[] amenities;

    RoomType(double price, String[] amenities) {
        this.price = price;
        this.amenities = amenities;
    }

    public double getPrice() {
        return price;
    }

    public String[] getAmenities() {
        return amenities;
    }
}
