package be.tomcools.dropwizard.websocket;

import io.dropwizard.setup.Environment;

public class WebsocketHandlerFactory {

    public WebsocketHandler forEnvironment(Environment environment) {
        return new WebsocketHandler(environment);
    }
}
