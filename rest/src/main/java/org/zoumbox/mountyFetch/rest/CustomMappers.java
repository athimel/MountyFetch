package org.zoumbox.mountyFetch.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
import org.zoumbox.mountyFetch.parser.Monster;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class CustomMappers implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
//        objectMapper.registerModule(new CustomModule());
    }

    public static class CustomModule extends SimpleModule {

        public CustomModule() {

            addSerializer(Monster.class, new JsonSerializer<>() {
                @Override
                public void serialize(Monster value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                    gen.writeStartObject();
                    gen.writeObjectField("elements", "toto");
//                    gen.writeObjectField("count", value.getCount());
//                    gen.writeObjectField("currentPage", value.getCurrentPage());
                    gen.writeEndObject();
                }
            });
        }

    }

}
