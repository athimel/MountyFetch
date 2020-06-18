package org.zoumbox.mountyFetch.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.Range;
import io.quarkus.jackson.ObjectMapperCustomizer;
import org.zoumbox.mountyFetch.parser.Monster;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Optional;

@Singleton
public class CustomMappers implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.registerModule(new CustomModule());
    }

    public static class MonsterSerializer extends JsonSerializer<Monster> {

        void writeObjectIfPresent(String name, Optional<?> value, JsonGenerator gen) throws IOException {
            if (value.isPresent()) {
                gen.writeObjectField(name, value.get());
            }
        }

        void writeRangeIfPresent(String name, Optional<Range<Integer>> value, JsonGenerator gen) throws IOException {
            if (value.isPresent()) {
                gen.writeObjectField(name, value.get().toString());
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
            writeRangeIfPresent("baseNival", value.baseNival(), gen);
            writeObjectIfPresent("template", value.template(), gen);
            writeObjectIfPresent("templateBonus", value.templateBonus(), gen);
            writeObjectIfPresent("age", value.age(), gen);
            writeObjectIfPresent("ageBonus", value.ageBonus(), gen);
            writeRangeIfPresent("nival", value.nival(), gen);
            gen.writeEndObject();
        }
    }

    public static class CustomModule extends SimpleModule {

        public CustomModule() {
            addSerializer(Monster.class, new MonsterSerializer());
        }

    }

}
