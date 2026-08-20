package com.catcher.miniserver.server;

import com.catcher.miniserver.validation.RequestShape;

public record Route (
     Handler handler,
     Class<? extends RequestShape> requestShape
    ) {}
