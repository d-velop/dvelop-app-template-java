package com.dvelop.archetype.cmd.awslambda;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {
    final ObjectMapper defaultObjectMapper;

    public JacksonObjectMapperProvider() {
        System.out.println("Register "+JacksonObjectMapperProvider.class.getSimpleName());
        defaultObjectMapper = createDefaultMapper();
    }

    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return result;
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("Retrieve defaultObjectMapper");
        return defaultObjectMapper;
    }
}
