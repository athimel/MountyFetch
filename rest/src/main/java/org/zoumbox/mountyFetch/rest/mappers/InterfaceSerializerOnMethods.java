package org.zoumbox.mountyFetch.rest.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class InterfaceSerializerOnMethods<T> extends StdSerializer<T> {

    private static final Log log = LogFactory.getLog(InterfaceSerializerOnMethods.class);

    protected Class<T> anInterface;
    protected List<Method> methods;

    protected InterfaceSerializerOnMethods(Class<T> t, String ... methodNames) throws NoSuchMethodException {
        super(t);
        this.anInterface = t;
        if (ArrayUtils.isEmpty(methodNames)) {
            this.methods = Arrays.asList(this.anInterface.getDeclaredMethods());
        } else {
            this.methods = new LinkedList<>();
            for (String methodName : methodNames) {
                Method method = this.anInterface.getDeclaredMethod(methodName);
                this.methods.add(method);
            }
        }
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (value == null) {
            return;
        }
        gen.writeStartObject();
        if (log.isDebugEnabled()) {
            log.debug("Methods in serialize for " + this.anInterface + ": " + methods);
        }
        methods.stream()
                .filter(m -> m.getParameters().length == 0)
                .filter(m -> !void.class.equals(m.getReturnType()))
                .forEach(method -> {
                    try {
                        Object methodResult = method.invoke(value);
                        if (methodResult instanceof com.google.common.base.Optional) {
                            methodResult = ((com.google.common.base.Optional<?>)methodResult).toJavaUtil();
                        }
                        if (methodResult instanceof Optional) {
                            Optional<?> optional = (Optional<?>)methodResult;
                            methodResult = optional.orElse(null);
                        }
                        if (methodResult != null) {
                            gen.writeObjectField(method.getName(), methodResult);
                        }
                    } catch (IllegalAccessException e) {
                        log.error("IllegalAccessException for :" + method.getName(), e);
                    } catch (InvocationTargetException e) {
                        log.error("InvocationTargetException for :" + method.getName(), e);
                    } catch (IOException e) {
                        log.error("IOException for :" + method.getName(), e);
                    }
                });
        gen.writeEndObject();

    }

}
