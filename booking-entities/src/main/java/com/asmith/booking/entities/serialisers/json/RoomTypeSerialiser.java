package com.asmith.booking.entities.serialisers.json;

import com.asmith.booking.entities.embeddables.RoomType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 * @author asmith
 */
public class RoomTypeSerialiser extends StdSerializer<RoomType> {

    public RoomTypeSerialiser() {
        super(RoomType.class);
    }

    @Override
    public void serialize(RoomType type, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        jg.writeFieldName("name");
        jg.writeString(type.name());
        jg.writeFieldName("price");
        jg.writeString(String.valueOf(type.getPrice()));
        jg.writeFieldName("amenities");
        jg.writeStartArray();
        for (String s : type.getAmenities()) {
            jg.writeString(s);
        }
        jg.writeEndArray();
        jg.writeEndObject();
    }

}
