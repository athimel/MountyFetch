package org.zoumbox.mountyFetch.rest.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.Range;
import io.quarkus.jackson.ObjectMapperCustomizer;
import org.zoumbox.mountyFetch.parser.Guilde;
import org.zoumbox.mountyFetch.parser.Monster;
import org.zoumbox.mountyFetch.parser.Position;
import org.zoumbox.mountyFetch.parser.Troll;
import org.zoumbox.mountyFetch.parser.Vue;
import org.zoumbox.mountyFetch.parser.WithLabel;

import javax.inject.Singleton;

@Singleton
public class CustomMappers implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.registerModule(new CustomModule());
    }

    public static class CustomModule extends SimpleModule {

        public CustomModule() {
            addSerializer(Range.class, new ToStringSerializer());
            addSerializer(WithLabel.class, new WithLabelSerializer());
            addSerializer(new InterfaceSerializerOnMethods<>(Position.class));
            addSerializer(new InterfaceSerializerOnMethods<>(Monster.class));
            addSerializer(new InterfaceSerializerOnMethods<>(Guilde.class));
            addSerializer(new InterfaceSerializerOnMethods<>(Troll.class));
            addSerializer(new InterfaceSerializerOnMethods<>(Vue.class));
        }

    }

}
