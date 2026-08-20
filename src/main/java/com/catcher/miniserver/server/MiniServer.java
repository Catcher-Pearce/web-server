package com.catcher.miniserver.server;

import com.catcher.miniserver.validation.RequestShape;
import com.catcher.miniserver.http.HttpMethod;


public class MiniServer {
    private final ServerEngine serverEngine;
    private final RequestDispatcher requestDispatcher = new RequestDispatcher();

    public MiniServer(int port) {
        this.serverEngine = new ServerEngine(port, requestDispatcher);
    }

    public void start() {
        serverEngine.start();
    }

    public void get(String path, Handler handler) {
        requestDispatcher.createRoute(path, HttpMethod.GET, handler, null);
    }

    public void post(
            String path,
            Handler handler,
            Class<? extends RequestShape> requestShape
    ) {
        requestDispatcher.createRoute(path, HttpMethod.POST, handler, requestShape);
    }

    public void patch(
            String path,
            Handler handler,
            Class<? extends RequestShape> requestShape
    ) {
        requestDispatcher.createRoute(path, HttpMethod.PATCH, handler, requestShape);
    }

    public void put(
            String path,
            Handler handler,
            Class<? extends RequestShape> requestShape
    ) {
        requestDispatcher.createRoute(path, HttpMethod.PUT, handler, requestShape);
    }

    public void delete(String path, Handler handler) {
        requestDispatcher.createRoute(path, HttpMethod.DELETE, handler, null);
    }
}
