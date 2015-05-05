package be.tomcools.dropwizard.websocket;

import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;

public class WebsocketHandler {
    private final ServerContainer container;

    public WebsocketHandler(ServerContainer container) {
        this.container = container;
    }

    public void addEndpoint(Class<?> aClass) {
    }
}
