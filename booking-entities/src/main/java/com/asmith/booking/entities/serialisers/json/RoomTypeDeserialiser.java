package com.asmith.booking.entities.serialisers.json;

import com.asmith.booking.entities.embeddables.RoomType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

/**
 * @author asmith
 */
public class RoomTypeDeserialiser extends StdDeserializer<RoomType> {

    public RoomTypeDeserialiser() {
        super(RoomType.class);
    }

    @Override
    public RoomType deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        switch (node.get("name").asText()) {
            case "SINGLE":
                return RoomType.SINGLE;
            case "DOUBLE":
                return RoomType.DOUBLE;
            case "DELUXE":
                return RoomType.DELUXE;
            case "EXECUTIVE":
                return RoomType.EXECUTIVE;
        }
        return null;
    }

}
