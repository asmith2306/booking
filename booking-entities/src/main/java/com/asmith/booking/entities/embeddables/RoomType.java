package com.asmith.booking.entities.embeddables;

import com.asmith.booking.entities.serialisers.json.RoomTypeSerialiser;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author asmith
 */
@JsonSerialize(using = RoomTypeSerialiser.class)
public enum RoomType {
    SINGLE("Single", 99.99, new String[]{"Bath towel", "Slippers", "Bathrobe", "Hairdryer"}),
    DOUBLE("Double", 119.99, new String[]{"Bath towels", "Slippers", "Bathrobes", "Hairdryer", "Iron", "Ironing board"}),
    DELUXE("Deluxe", 149.99, new String[]{"Bath towels", "Slippers", "Bathrobes", "Hairdryer", "Iron", "Ironing board", "Sleepware", "Toiletries", "Safe"}),
    EXECUTIVE("Executive", 199.99, new String[]{"Bath towels", "Slippers", "Bathrobes", "Hairdryer", "Iron", "Ironing board", "Sleepware", "Toiletries", "Safe",
        "Fan", "HDTV", "Stocked refrigerator", "Jacuzzi bath"});

    private final String name;
    private final double price;
    private final String[] amenities;

    RoomType(String name, double price, String[] amenities) {
        this.name = name;
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
