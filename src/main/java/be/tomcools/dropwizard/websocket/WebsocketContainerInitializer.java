package be.tomcools.dropwizard.websocket;


import io.dropwizard.jetty.MutableServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class WebsocketContainerInitializer {
    public ServerContainer initialize(MutableServletContextHandler contextHandler) {
        try {
            return WebSocketServerContainerInitializer.configureContext(contextHandler);
        } catch (Exception e) {
            throw new IllegalStateException("Could not initialize contexthandler to enable Websockets", e);
        }
    }
}
