package org.zoumbox.mountyFetch.rest.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.zoumbox.mountyFetch.parser.WithLabel;

import java.io.IOException;

public class WithLabelSerializer extends JsonSerializer<WithLabel> {

    @Override
    public void serialize(final WithLabel src, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String label = src.getLabel();
        gen.writeString(label);
    }

}
