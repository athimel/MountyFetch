package org.zoumbox.mountyFetch.rest.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.zoumbox.mountyFetch.parser.Position;

import java.io.IOException;

public class PositionSerializer extends JsonSerializer<Position> {

    @Override
    public void serialize(Position value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("x", value.x());
        gen.writeObjectField("y", value.y());
        gen.writeObjectField("n", value.n());
        gen.writeEndObject();
    }
}
