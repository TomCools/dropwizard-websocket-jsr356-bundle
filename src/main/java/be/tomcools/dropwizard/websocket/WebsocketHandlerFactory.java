package be.tomcools.dropwizard.websocket;

import io.dropwizard.core.setup.Environment;

public class WebsocketHandlerFactory {

    public WebsocketHandler forEnvironment(WebsocketConfiguration configuration, Environment environment) {
        return new WebsocketHandler(configuration, environment);
    }
}
