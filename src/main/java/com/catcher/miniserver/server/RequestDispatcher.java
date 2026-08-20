package com.catcher.miniserver.server;

import com.catcher.miniserver.validation.RequestShape;
import com.catcher.miniserver.http.HttpMethod;
import com.catcher.miniserver.http.HttpRequest;
import com.catcher.miniserver.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class RequestDispatcher {
    Map<String, Map<HttpMethod, Route>> routeMap;
    RequestMapper requestMapper;

    public RequestDispatcher() {
        this.routeMap = new HashMap<>();
        this.requestMapper = new RequestMapper();
    }

    public void createRoute(String path, HttpMethod method, Handler handler, Class<? extends RequestShape> requestShape) {
        if (!routeMap.containsKey(path)) {
            routeMap.put(path, new HashMap<>(Map.of(method, new Route(handler, requestShape))));
        } else {
            routeMap.get(path).put(method, new Route(handler, requestShape));
        }
    }

    public HttpResponse handleRequest(HttpRequest request) {
        try {
            Route route = routeMap.get(request.path()).get(request.method());
            ServerRequest serverRequest = requestMapper.map(route, request);

            return route.handler().handle(serverRequest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
