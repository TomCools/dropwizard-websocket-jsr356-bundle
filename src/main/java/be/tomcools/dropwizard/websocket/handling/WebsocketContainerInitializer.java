package be.tomcools.dropwizard.websocket.handling;


import io.dropwizard.jetty.MutableServletContextHandler;
import org.eclipse.jetty.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;

public class WebsocketContainerInitializer {
    public void initialize(MutableServletContextHandler contextHandler,
                           JakartaWebSocketServletContainerInitializer.Configurator configurator) {
        try {
            JakartaWebSocketServletContainerInitializer.configure(contextHandler, configurator);
        } catch (Exception e) {
            throw new IllegalStateException("Could not initialize context handler to enable Websockets", e);
        }
    }
}
