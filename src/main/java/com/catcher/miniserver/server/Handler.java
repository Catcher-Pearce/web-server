package com.catcher.miniserver.server;

import com.catcher.miniserver.http.HttpResponse;

@FunctionalInterface
public interface Handler {
    public HttpResponse handle(ServerRequest serverRequest);
}
