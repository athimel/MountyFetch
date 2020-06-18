package org.zoumbox.mountyFetch.rest.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ToStringSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.toString());
    }

}
