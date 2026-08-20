package com.catcher.miniserver.server;

import com.catcher.miniserver.http.HttpRequest;

public class RequestMapper {
    public ServerRequest map(Route route, HttpRequest request) {
        return new ServerRequest(
                request.method(),
                request.path(),
                null,
                null,
                request.headers(),
                request.body()
        );
    }
}
