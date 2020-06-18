package org.zoumbox.mountyFetch.rest.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.zoumbox.mountyFetch.parser.Monster;

import java.io.IOException;
import java.util.Optional;

public class MonsterSerializer extends JsonSerializer<Monster> {

    void writeObjectIfPresent(String name, Optional<?> value, JsonGenerator gen) throws IOException {
        if (value.isPresent()) {
            gen.writeObjectField(name, value.get());
        }
    }

    @Override
    public void serialize(Monster value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        writeObjectIfPresent("id", value.id(), gen);
        gen.writeObjectField("fullName", value.fullName());
        writeObjectIfPresent("position", value.position(), gen);
        writeObjectIfPresent("family", value.family(), gen);
        writeObjectIfPresent("baseName", value.baseName(), gen);
        writeObjectIfPresent("baseNival", value.baseNival(), gen);
        writeObjectIfPresent("template", value.template(), gen);
        writeObjectIfPresent("templateBonus", value.templateBonus(), gen);
        writeObjectIfPresent("age", value.age(), gen);
        writeObjectIfPresent("ageBonus", value.ageBonus(), gen);
        writeObjectIfPresent("nival", value.nival(), gen);
        gen.writeEndObject();
    }
}
