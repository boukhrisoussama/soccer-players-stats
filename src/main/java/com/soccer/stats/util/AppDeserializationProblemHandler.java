package com.soccer.stats.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class AppDeserializationProblemHandler extends DeserializationProblemHandler {

    @Override
    public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
        log.error("-- Unknown property: {} of object {}", propertyName, beanOrClass.getClass());
        return super.handleUnknownProperty(ctxt, p, deserializer, beanOrClass, propertyName);
    }

    @Override
    public Object handleInstantiationProblem(DeserializationContext ctxt, Class<?> instClass, Object argument, Throwable t) throws IOException {
        log.error("-- Instantiation problem: {} ", t.getMessage());
        return super.handleInstantiationProblem(ctxt, instClass, argument, t);
    }
}
