package be.tomcools.dropwizard.websocket;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebsocketBundle implements Bundle {
    private WebsocketHandlerFactory handlerFactory = new WebsocketHandlerFactory();

    private WebsocketHandler handler;

    public WebsocketBundle() {
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(Environment environment) {
        handler = handlerFactory.forEnvironment(environment);
    }

    public void addEndpoint(Class<?> aClass) {
        handler.addEndpoint(aClass);
    }
}
