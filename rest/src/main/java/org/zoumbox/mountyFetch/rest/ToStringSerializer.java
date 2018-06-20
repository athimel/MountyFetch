package org.zoumbox.mountyFetch.rest;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ToStringSerializer implements JsonSerializer<Object> {

    @Override
    public JsonElement serialize(final Object src, final Type typeOfSrc, final JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

}
