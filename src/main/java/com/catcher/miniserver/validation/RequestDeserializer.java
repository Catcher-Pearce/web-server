package com.catcher.miniserver.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestDeserializer {

    private final ObjectMapper objectMapper;

    public RequestDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    public RequestShape deserialize(
            String body,
            String contentType,
            Class<? extends RequestShape> requestShape
    ) throws JsonProcessingException {

        if (requestShape == null) {
            return null;
        }

        if (contentType == null) {
            throw new IllegalArgumentException("Missing Content-Type");
        }

        if (contentType.startsWith("application/json")) {
            return objectMapper.readValue(body, requestShape);
        }

        throw new IllegalArgumentException(
                "Unsupported Content-Type: " + contentType
        );
    }
}
