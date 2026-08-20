package com.catcher.miniserver.http;

import java.util.Map;

public record HttpResponse (
        int status,
        Map<String, String> headers,
        Object body
) {
    public HttpResponse(int status) {
        this(status, null, null);
    }
}


