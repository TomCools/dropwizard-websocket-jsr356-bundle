package be.tomcools.dropwizard.websocket.handling;


import be.tomcools.dropwizard.websocket.WebsocketConfiguration;
import io.dropwizard.jetty.MutableServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class WebsocketContainerInitializer {
    public WebsocketContainer initialize(WebsocketConfiguration configuration,
                                         MutableServletContextHandler contextHandler) {
        try {
            return new WebsocketContainer(configuration, WebSocketServerContainerInitializer.configureContext(contextHandler));
        } catch (Exception e) {
            throw new IllegalStateException("Could not initialize contexthandler to enable Websockets", e);
        }
    }
}
